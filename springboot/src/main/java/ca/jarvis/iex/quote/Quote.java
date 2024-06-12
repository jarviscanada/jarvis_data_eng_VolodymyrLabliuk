package ca.jarvis.iex.quote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @Column(name = "ticker", nullable = false)
    private String ticker; //primary key
    @Column(name = "last_price", nullable = false)
    private Double lastPrice;
    @Column(name = "bid_price", nullable = false)
    private Double bidPrice;
    @Column(name = "bid_size", nullable = false)
    private Integer bidSize;
    @Column(name = "ask_price", nullable = false)
    private Double askPrice;
    @Column(name = "ask_size", nullable = false)
    private Integer askSize;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getBidSize() {
        return bidSize;
    }

    public void setBidSize(Integer bidSize) {
        this.bidSize = bidSize;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Integer getAskSize() {
        return askSize;
    }

    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }
}
