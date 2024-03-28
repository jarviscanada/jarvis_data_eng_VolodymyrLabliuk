package ca.jrvs.apps.jdbc;


import okhttp3.OkHttpClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Request;
import okhttp3.Response;

import static ca.jrvs.apps.jdbc.Main.logger;

public class QuoteHttpHelper {

    //private String apiKey = "4edeebbaf9msh53822f7bf795ee1p1d968djsn9190c9150d97";
    private String apiKey;
    private OkHttpClient client;

    public QuoteHttpHelper() {
        this.client = new OkHttpClient();
    }

    public QuoteHttpHelper(String apiKey, OkHttpClient client) {
        this.apiKey = apiKey;
        this.client = client;
    }
    /**
     * Get Quote data from Alpha Vantage endpoint
     * @param symbol
     * @return JSON to with Quote data
     * @throws JsonProcessingException - if problem with returning json
     */

    private String getQuoteJson(String symbol) throws JsonProcessingException {
        Request request = new Request.Builder()
                .url("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&datatype=json")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {
        try {
            String json = getQuoteJson(symbol);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
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
        } catch (JsonProcessingException e) {
            logger.error("Error: Mapping error", e);
        }
        return null;
    }
}
