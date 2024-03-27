package ca.jrvs.apps.jdbc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Timestamp;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "01. symbol",
        "02. open",
        "03. high",
        "04. low",
        "05. price",
        "06. volume",
        "07. latest trading day",
        "08. previous close",
        "09. change",
        "10. change percent",
        "11. timestamp"
})
public class Quote {
    @JsonProperty("01. symbol")
    private String ticker; //id
    @JsonProperty("02. open")
    private double open;
    @JsonProperty("03. high")
    private double high;
    @JsonProperty("04. low")
    private double low;
    @JsonProperty("05. price")
    private double price;
    @JsonProperty("06. volume")
    private int volume;
    @JsonProperty("07. latest trading day")
    private Date latestTradingDay;
    @JsonProperty("08. previous close")
    private double previousClose;
    @JsonProperty("09. change")
    private double change;
    @JsonProperty("10. change percent")
    private String changePercent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonProperty("11. timestamp")
    private Timestamp timestamp; //time when the info was pulled

    public Quote() {
    }

    public Quote(String ticker, double open, double high, double low, double price, int volume, Date latestTradingDay,
                 double previousClose, double change, String changePercent, Timestamp timestamp) {
        this.ticker = ticker;
        this.open = open;
        this.high = high;
        this.low = low;
        this.price = price;
        this.volume = volume;
        this.latestTradingDay = latestTradingDay;
        this.previousClose = previousClose;
        this.change = change;
        this.changePercent = changePercent;
        this.timestamp = timestamp;
    }

    @JsonProperty("01. symbol")
    public String getTicker() {
        return ticker;
    }
    @JsonProperty("01. symbol")
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @JsonProperty("02. open")
    public double getOpen() {
        return open;
    }

    @JsonProperty("02. open")
    public void setOpen(double open) {
        this.open = open;
    }

    @JsonProperty("03. high")
    public double getHigh() {
        return high;
    }

    @JsonProperty("03. high")
    public void setHigh(double high) {
        this.high = high;
    }

    @JsonProperty("04. low")
    public double getLow() {
        return low;
    }

    @JsonProperty("04. low")
    public void setLow(double low) {
        this.low = low;
    }

    @JsonProperty("05. price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("05. price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("06. volume")
    public int getVolume() {
        return volume;
    }

    @JsonProperty("06. volume")
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @JsonProperty("07. latest trading day")
    public Date getLatestTradingDay() {
        return latestTradingDay;
    }

    @JsonProperty("07. latest trading day")
    public void setLatestTradingDay(Date latestTradingDay) {
        this.latestTradingDay = latestTradingDay;
    }

    @JsonProperty("08. previous close")
    public double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("08. previous close")
    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("09. change")
    public double getChange() {
        return change;
    }

    @JsonProperty("09. change")
    public void setChange(double change) {
        this.change = change;
    }

    @JsonProperty("10. change percent")
    public String getChangePercent() {
        return changePercent;
    }

    @JsonProperty("10. change percent")
    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonProperty("11. timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonProperty("11. timestamp")
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}