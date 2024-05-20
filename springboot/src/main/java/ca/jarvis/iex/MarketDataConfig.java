package ca.jarvis.iex;

public class MarketDataConfig {

    private final String apiUrl;
    private final String token;

    public MarketDataConfig() {
        this.apiUrl = "https://api.iex.cloud/v1/data/core/";
        this.token = "pk_e986b52d0c3a4c2f8a856091cc1e223d";
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getToken() {
        return token;
    }
}

