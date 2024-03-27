package ca.jrvs.apps.jdbc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Date;
import java.sql.Timestamp;

public class QuoteServiceUnitTest {
    private QuoteDao quoteDao;
    private QuoteService quoteService;

    private QuoteHttpHelper httpHelper;

    private OkHttpClient client;

    @Before
    public void setUp() {
        client = mock(OkHttpClient.class);
        quoteDao = new QuoteDao();
        httpHelper = new QuoteHttpHelper(client);
        quoteService = new QuoteService(quoteDao, httpHelper);
    }

    @Test
    public void testSaveQuote() {
        Quote quote = new Quote("MSFT", 332.3800, 333.8300, 326.3600,
                327.7300, 21085695,new Date(2023-10-13),
                331.1600, -3.4300, "-1.0358%", new Timestamp(System.currentTimeMillis()));
        assertDoesNotThrow(()->{quoteService.save(quote);});
    }

    @Test
    public void testQuoteIsNull() {
        Quote quote = null;
        assertThrows(NullPointerException.class, (Executable) ()->{quoteService.save(quote);});
//        assertDoesNotThrow(()->{quoteService.save(quote);});
    }

    @Test
    public void testFindByIdNoSymbol() {
        assertThrows(IllegalArgumentException.class, (Executable) ()->{quoteService.findById(null);});
    }

    @Test
    public void testDeleteByIdNoSymbol() {
        assertThrows(IllegalArgumentException.class, (Executable) ()->{quoteService.deleteById(null);});
    }

    @Test
    public void testDeleteById() {
        quoteService.deleteById("AAPL");
        boolean quoteFound = quoteService.findById("AAPL").isPresent();
        Assert.assertTrue(quoteFound);
        //assertDoesNotThrow(()->{quoteService.deleteById("AAPL");});
    }

//    @Test
//    public void testQuoteFindById() {
//        Quote quote = quoteService.findById("AAPL").isPresent() ?
//                quoteService.findById("AAPL").get() : null;
//        if(quote != null)
//            assertEquals(quote.getTicker(), "AAPL");
//        else {
//            Quote quoteToAdd = new Quote("AAPL", 332.3800, 333.8300, 326.3600,
//                    327.7300, 21085695,new Date(2023-10-13),
//                    331.1600, -3.4300, "-1.0358%", new Timestamp(System.currentTimeMillis()));
//            quoteService.save(quoteToAdd);
//            assertEquals(quote.getTicker(), "AAPL");
//        }
//    }

}

