package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.IllegalArgumentException;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    private final MarketDataDao marketDataDao;
    private final QuoteDao quoteDao;

    @Value("${app.init.dailyList}")
    private String[] initDailyList;

    public QuoteService(MarketDataDao marketDataDao, QuoteDao quoteDao) {
        this.marketDataDao = marketDataDao;
        this.quoteDao = quoteDao;
    }

    public IexQuote getQuote(String symbol) throws Exception {
        IexQuote quote = marketDataDao.findById(symbol).get();
        //quoteDao.save(quote);
        return quote;
    }
    public List<IexQuote> getQuotes(Iterable<String> tickers) throws Exception {
        List<IexQuote> quotes = marketDataDao.findAllById(tickers);
        //quoteDao.saveAll(quotes);
        return quotes;
    }

    /**
     * Update quote table against IEX source
     *
     * - get all quotes from the db
     * - for each ticker get IexQuote
     * - convert IexQuote to Quote entity
     * - persist quote to db
     *
     * @throws ResourceNotFoundException if ticker is not found from IEX
     * @throws DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void updateMarketData() {
        List<Quote> quotes = quoteDao.findAll();
        List<String> tickers = quotes.stream()
                .map(Quote::getTicker) // Assuming getTicker() method exists in Quote class
                .collect(Collectors.toList());
        saveQuotes(tickers);
    }

    /**
     * Validate (against IEX) and save given tickers to quote table
     *
     * - get IexQuote(s)
     * - convert each IexQuote to Quote entity
     * - persist the quote to db
     *
     * @param tickers
     * @return list of converted quote entities
     * @throws IllegalArgumentException if ticker is not found from IEX
     */
    public List<Quote> saveQuotes(List<String> tickers) {
        List<Quote> quotes;
        try {
            List<IexQuote> iexQuotes = marketDataDao.findAllById(tickers);
            quotes = new ArrayList<>();
            for(IexQuote iexQuote : iexQuotes){
                quotes.add(buildQuoteFromIexQuote(iexQuote));
            }
            quoteDao.saveAll(quotes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return quotes;
    }

    /**
     * Find an IexQuote from the given ticker
     *
     * @param ticker
     * @return corresponding IexQuote object
     * @throws IllegalArgumentException if ticker is invalid
     */
    public IexQuote findIexQuoteByTicker(String ticker) {
        IexQuote quote;
        try {
            quote = marketDataDao.findById(ticker).get();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        //quoteDao.save(quote);
        return quote;
    }

    /**
     * Update a given quote to the quote table without validation
     *
     * @param quote entity to save
     * @return the saved quote entity
     */
    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }

    /**
     * Find all quotes from the quote table
     *
     * @return a list of quotes
     */
    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

    /**
     * Helper method to map an IexQuote to a Quote entity
     * Note: 'iexQuote.getLatestPrice() == null' if the stock market is closed
     * Make sure to set a default value for number field(s)
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        Quote quote = new Quote();
        quote.setTicker(iexQuote.getSymbol());
        quote.setAskPrice(iexQuote.getIexAskPrice());
        quote.setAskSize(iexQuote.getIexAskSize());
        quote.setBidPrice(iexQuote.getIexBidPrice());
        quote.setBidSize(iexQuote.getIexBidSize());
        quote.setLastPrice(iexQuote.getLatestPrice());
        return quote;
    }

    /**
     * Helper method to validate and save a single ticker
     * Not to be confused with saveQuote(Quote quote)
     */
    protected Quote saveQuote(String ticker) {
        IexQuote iexQuote = findIexQuoteByTicker(ticker);
        if(iexQuote != null){
            Quote quote = buildQuoteFromIexQuote(iexQuote);
            saveQuote(quote);
            return quote;
        }
        return null;
    }
}
