package ca.jarvis.iex;

import javax.persistence.*;

@Entity
@Table(name = "position")
@IdClass(PositionId.class)
public class Position {

    @Id
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    @Id
    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "position", nullable = false)
    private Integer position;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

