package ca.jarvis.iex.trader;

import ca.jarvis.iex.account.Account;

public class TraderAccountView {

    private Trader trader;
    private Account account;

    public TraderAccountView() {
    }

    public TraderAccountView(Trader trader, Account account) {
        this.trader = trader;
        this.account = account;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "TraderAccountView{" +
                "trader=" + trader +
                ", account=" + account +
                '}';
    }
}

