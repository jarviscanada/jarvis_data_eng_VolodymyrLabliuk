package ca.jarvis.iex;

public class MarketDataConfig {

    private final String apiUrl;
    private final String token;

    public MarketDataConfig() {
        this.apiUrl = "https://api.iex.cloud/v1/data/core/";
        this.token = "pk_0077103aed4f422493fa392b4edb5f1c";
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getToken() {
        return token;
    }
}

