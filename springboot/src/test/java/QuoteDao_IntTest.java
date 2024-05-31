import ca.jarvis.iex.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDao_IntTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;

    @Before
    public void setup() {
       savedQuote = new Quote();
       savedQuote.setBidSize(10);
       savedQuote.setBidPrice(10.0);
       savedQuote.setAskSize(10);
       savedQuote.setLastPrice(10.0);
       savedQuote.setTicker("AAPl");
    }
    @After
    public void wrapup() {
        quoteDao.deleteById(savedQuote.getTicker());
    }
//    @BeforeEach
//    public void setup() {
//        quoteDao.deleteAll();
//    }

    @Test
    public void testSaveAndFind() {
        Quote quote = new Quote();
        quote.setTicker("AAPL");
        quote.setLastPrice(150.00);

        quoteDao.save(quote);

        Quote savedQuote = quoteDao.findById("AAPL").orElse(null);
        assertNotNull(savedQuote);
        assertEquals("AAPL", savedQuote.getTicker());
        assertEquals(150.00, savedQuote.getLastPrice(), 0.001);
    }

    @Test
    public void testFindAll() {
        Quote quote1 = new Quote();
        quote1.setTicker("AAPL");
        quote1.setLastPrice(150.00);

        Quote quote2 = new Quote();
        quote2.setTicker("GOOGL");
        quote2.setLastPrice(2800.00);

        quoteDao.saveAll(Arrays.asList(quote1, quote2));

        List<Quote> quotes = quoteDao.findAll();
        assertEquals(2, quotes.size());
        assertTrue(quotes.stream().anyMatch(q -> "AAPL".equals(q.getTicker()) && q.getLastPrice().equals(150.00)));
        assertTrue(quotes.stream().anyMatch(q -> "GOOGL".equals(q.getTicker()) && q.getLastPrice().equals(2800.00)));
    }

    @Test
    public void testDeleteByTicker() {
        assertDoesNotThrow(()->{quoteDao.deleteById("AAPL");});
        assertNull(quoteDao.findQuoteByTicker("AAPL"));
    }
}
