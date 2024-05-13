package ca.jarvis.iex;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final MarketDataDao marketDataDao;

    public QuoteService(MarketDataDao marketDataDao) {
        this.marketDataDao = marketDataDao;
    }

    public IexQuote getQuote(String symbol) throws Exception {
        return marketDataDao.findById(symbol).get();
    }
    public List<IexQuote> getQuotes(Iterable<String> tickers) throws Exception {
        return marketDataDao.findAllById(tickers);
    }
}
