package ca.jarvis.iex.utils;

import ca.jarvis.iex.config.MarketDataConfig;
import ca.jarvis.iex.iexQuote.IexQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketDataDao{

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final MarketDataConfig marketDataConfig;


    @Autowired
    public MarketDataDao(RestTemplate restTemplate, ObjectMapper objectMapper, MarketDataConfig marketDataConfig) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.marketDataConfig = marketDataConfig;
    }

    /**
     * Get an IexQuote
     *
     * @param ticker
     * @throws IllegalArgumentException if a given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    public Optional<IexQuote> findById(String ticker) throws IOException {
        String url = marketDataConfig.getApiUrl() + "quote/" + ticker + "?token=" + marketDataConfig.getToken();
        String responseBody = executeHttpGet(url).get();
        IexQuote[] quotes = objectMapper.readValue(responseBody, IexQuote[].class);
        return Optional.ofNullable(quotes[0]);
    }

    /**
     * Get quotes from IEX
     * @param tickers is a list of tickers
     * @return a list of IexQuote objects
     * @throws IllegalArgumentException if a given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    public List<IexQuote> findAllById(Iterable<String> tickers) throws IOException {
        String url = marketDataConfig.getApiUrl() + "quote/" + String.join(",", tickers) + "?token=" + marketDataConfig.getToken();
        String responseBody = executeHttpGet(url).get();
        IexQuote[] quotes = objectMapper.readValue(responseBody, IexQuote[].class);
        return Arrays.asList(quotes);
    }

    /**
     * Execute a GET request and return http entity/body as a string
     * Tip: use EntitiyUtils.toString to process HTTP entity
     *
     * @param url resource URL
     * @return http response body or Optional.empty for 404 response
     * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
     */
    private Optional<String> executeHttpGet(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();
        if(responseEntity.getStatusCodeValue() == 404)
            return Optional.empty();
        return Optional.ofNullable(responseBody);
    }
}

