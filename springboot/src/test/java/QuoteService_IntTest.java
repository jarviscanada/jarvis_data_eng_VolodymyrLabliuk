import ca.jarvis.iex.iexQuote.IexQuote;
import ca.jarvis.iex.quote.Quote;
import ca.jarvis.iex.quote.QuoteDao;
import ca.jarvis.iex.quote.QuoteService;
import ca.jarvis.iex.utils.MarketDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
class QuoteService_IntTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private MarketDataDao marketDataDao;

    @Autowired
    private QuoteDao quoteDao;

    @Test
    public void updateMarketData() throws IOException {
        List<String> tickers = Arrays.asList("AAPL", "GOOGL");

        IexQuote iexQuote1 = new IexQuote();
        iexQuote1.setSymbol("AAPL");
        iexQuote1.setLatestPrice(150.00);

        IexQuote iexQuote2 = new IexQuote();
        iexQuote2.setSymbol("GOOGL");
        iexQuote2.setLatestPrice(2800.00);

        when(marketDataDao.findAllById(tickers)).thenReturn(Arrays.asList(iexQuote1, iexQuote2));

        quoteService.updateMarketData();

        List<Quote> quotes = quoteDao.findAll();
        assertEquals(2, quotes.size());
        assertTrue(quotes.stream().anyMatch(q -> "AAPL".equals(q.getTicker()) && q.getLastPrice().equals(150.00)));
        assertTrue(quotes.stream().anyMatch(q -> "GOOGL".equals(q.getTicker()) && q.getLastPrice().equals(2800.00)));
    }

    @Test
    public void saveQuotes() {
        Quote quote1 = new Quote();
        quote1.setTicker("AAPL");
        quote1.setLastPrice(150.00);

        Quote quote2 = new Quote();
        quote2.setTicker("GOOGL");
        quote2.setLastPrice(2800.00);

        List<String> quotes = Arrays.asList(quote1.getTicker(), quote2.getTicker());
        quoteService.saveQuotes(quotes);

        List<Quote> savedQuotes = quoteDao.findAll();
        assertEquals(2, savedQuotes.size());
        assertTrue(savedQuotes.stream().anyMatch(q -> "AAPL".equals(q.getTicker()) && q.getLastPrice().equals(150.00)));
        assertTrue(savedQuotes.stream().anyMatch(q -> "GOOGL".equals(q.getTicker()) && q.getLastPrice().equals(2800.00)));
    }

    @Test
    public void saveQuote() {
        Quote quote = new Quote();
        quote.setTicker("AAPL");
        quote.setLastPrice(150.00);

        quoteService.saveQuote(quote);

        Quote savedQuote = quoteDao.findById("AAPL").orElse(null);
        assertNotNull(savedQuote);
        assertEquals("AAPL", savedQuote.getTicker());
        assertEquals(150.00, savedQuote.getLastPrice(), 0.001);
    }

    @Test
    public void findAllQuotes() {
        Quote quote1 = new Quote();
        quote1.setTicker("AAPL");
        quote1.setLastPrice(150.00);

        Quote quote2 = new Quote();
        quote2.setTicker("GOOGL");
        quote2.setLastPrice(2800.00);

        quoteDao.saveAll(Arrays.asList(quote1, quote2));

        List<Quote> quotes = quoteService.findAllQuotes();
        assertEquals(2, quotes.size());
        assertTrue(quotes.stream().anyMatch(q -> "AAPL".equals(q.getTicker()) && q.getLastPrice().equals(150.00)));
        assertTrue(quotes.stream().anyMatch(q -> "GOOGL".equals(q.getTicker()) && q.getLastPrice().equals(2800.00)));
    }
}


