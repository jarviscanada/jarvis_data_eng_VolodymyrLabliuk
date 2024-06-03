package ca.jarvis.iex;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PositionId implements Serializable {

    private Long accountId;
    private String ticker;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
