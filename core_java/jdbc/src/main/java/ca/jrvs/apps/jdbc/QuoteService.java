package ca.jrvs.apps.jdbc;

import java.util.Optional;

public class QuoteService {

    private QuoteDao dao;
    private QuoteHttpHelper httpHelper;

    public QuoteService(QuoteDao dao, QuoteHttpHelper httpHelper){
        this.dao = dao;
        this.httpHelper = httpHelper;
    }

    /**
     * Fetches latest quote data from endpoint
     * @param ticker
     * @return Latest quote information or empty optional if ticker symbol not found
     */
    public Optional<Quote> fetchQuoteDataFromAPI(String ticker) {
        return Optional.ofNullable(httpHelper.fetchQuoteInfo(ticker));
    }

    public Quote save(Quote entity) throws IllegalArgumentException {
        return dao.save(entity);
    }
    public Optional<Quote> findById(String s) throws IllegalArgumentException {
        return dao.findById(s);
    }
    public Iterable<Quote> findAll() {
        return dao.findAll();
    }
    public void deleteById(String s) throws IllegalArgumentException {
        dao.deleteById(s);
    }
    public void deleteAll() {
        dao.deleteAll();
    }
}
