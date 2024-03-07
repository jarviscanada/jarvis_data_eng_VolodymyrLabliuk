package ca.jrvs.apps.jdbc;


import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class QuoteHttpHelper {

    private String apiKey = "4edeebbaf9msh53822f7bf795ee1p1d968djsn9190c9150d97";
    private OkHttpClient client;

    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol+"&datatype=json"))
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String jsonToParse = "";

            if (jsonNode.has("Global Quote")) {
                JsonNode globalQuoteNode = jsonNode.get("Global Quote");

                if (globalQuoteNode.isObject()) {
                    ObjectNode globalQuoteObject = (ObjectNode) globalQuoteNode;
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String value = String.valueOf(timestamp);
                    globalQuoteObject.put("11. timestamp",value);
                    jsonToParse = objectMapper.writeValueAsString(globalQuoteObject);
                }
            }

            return objectMapper.readValue(jsonToParse, Quote.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        QuoteHttpHelper quoteHttpHelper = new QuoteHttpHelper();
        Quote quote = quoteHttpHelper.fetchQuoteInfo("MSFT");
        System.out.println(quote.getTicker());
    }

}
