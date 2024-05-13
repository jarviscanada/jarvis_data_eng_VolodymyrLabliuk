package ca.jarvis.iex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class IexQuote {
    private int avgTotalVolume;
    private String calculationPrice;
    private double change;
    private double changePercent;
    private Double close;
    private String closeSource;
    private Long closeTime;
    private String companyName;
    private String currency;
    private Double delayedPrice;
    private Long delayedPriceTime;
    private Double extendedChange;
    private Double extendedChangePercent;
    private Double extendedPrice;
    private Long extendedPriceTime;
    private Double high;
    private String highSource;
    private Long highTime;
    private Double iexAskPrice;
    private Integer iexAskSize;
    private Double iexBidPrice;
    private Integer iexBidSize;
    private Double iexClose;
    private Long iexCloseTime;
    private Long iexLastUpdated;
    private Double iexMarketPercent;
    private Double iexOpen;
    private Long iexOpenTime;
    private Double iexRealtimePrice;
    private Integer iexRealtimeSize;
    private Integer iexVolume;
    private Long lastTradeTime;
    private Double latestPrice;
    private String latestSource;
    private String latestTime;
    private Long latestUpdate;
    private Integer latestVolume;
    private Double low;
    private String lowSource;
    private Long lowTime;
    private Long marketCap;
    private Double oddLotDelayedPrice;
    private Long oddLotDelayedPriceTime;
    private Double open;
    private Long openTime;
    private String openSource;
    private Double peRatio;
    private Double previousClose;
    private Integer previousVolume;
    private String primaryExchange;
    private String symbol;
    private Integer volume;
    private Double week52High;
    private Double week52Low;
    private Double ytdChange;
    private boolean isUSMarketOpen;

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("change")
    public double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("closeSource")
    public String getCloseSource() {
        return closeSource;
    }

    @JsonProperty("closeSource")
    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    @JsonProperty("closeTime")
    public Long getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("delayedPrice")
    public Double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedChange")
    public Double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPrice")
    public Double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedPriceTime")
    public Long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("highSource")
    public String getHighSource() {
        return highSource;
    }

    @JsonProperty("highSource")
    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    @JsonProperty("highTime")
    public Long getHighTime() {
        return highTime;
    }

    @JsonProperty("highTime")
    public void setHighTime(Long highTime) {
        this.highTime = highTime;
    }

    @JsonProperty("iexAskPrice")
    public Double getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Double iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public Integer getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Integer iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("iexBidPrice")
    public Double getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Double iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public Integer getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Integer iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexClose")
    public Double getIexClose() {
        return iexClose;
    }

    @JsonProperty("iexClose")
    public void setIexClose(Double iexClose) {
        this.iexClose = iexClose;
    }

    @JsonProperty("iexCloseTime")
    public Long getIexCloseTime() {
        return iexCloseTime;
    }

    @JsonProperty("iexCloseTime")
    public void setIexCloseTime(Long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    @JsonProperty("iexLastUpdated")
    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexOpen")
    public Double getIexOpen() {
        return iexOpen;
    }

    @JsonProperty("iexOpen")
    public void setIexOpen(Double iexOpen) {
        this.iexOpen = iexOpen;
    }

    @JsonProperty("iexOpenTime")
    public Long getIexOpenTime() {
        return iexOpenTime;
    }

    @JsonProperty("iexOpenTime")
    public void setIexOpenTime(Long iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public Integer getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Integer iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexVolume")
    public Integer getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Integer iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public Long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Integer getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Integer latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("lowSource")
    public String getLowSource() {
        return lowSource;
    }

    @JsonProperty("lowSource")
    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    @JsonProperty("lowTime")
    public Long getLowTime() {
        return lowTime;
    }

    @JsonProperty("lowTime")
    public void setLowTime(Long lowTime) {
        this.lowTime = lowTime;
    }

    @JsonProperty("marketCap")
    public Long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("oddLotDelayedPrice")
    public Double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public Long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(Long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }
    @JsonProperty("open")
    public Double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Double open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Long getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("openSource")
    public String getOpenSource() {
        return openSource;
    }

    @JsonProperty("openSource")
    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("previousClose")
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Integer getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Integer previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("volume")
    public Integer getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public Double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("avgTotalVolume")
    public int getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(int avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    // Similar annotations for other properties...

    @JsonProperty("lastTradeTime")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date getLastTradeTime() {
        return new Date(lastTradeTime);
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("isUSMarketOpen")
    public boolean getIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    @JsonProperty("isUSMarketOpen")
    public void setIsUSMarketOpen(boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

}

