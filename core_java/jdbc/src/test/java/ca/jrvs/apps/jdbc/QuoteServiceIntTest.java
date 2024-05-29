package ca.jrvs.apps.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class QuoteServiceIntTest {
    private QuoteDao quoteDao;
    private QuoteService quoteService;

    private QuoteHttpHelper httpHelper;

    private OkHttpClient client;
    private Connection c;

    private String url = "jdbc:postgresql://localhost:5432/stock_quote";
    private String username = "postgres";
    private String password = "password";

    private String apiKey = "4edeebbaf9msh53822f7bf795ee1p1d968djsn9190c9150d97";

    @Before
    public void setUp() throws SQLException {
        client = new OkHttpClient();
        c = DriverManager.getConnection(url, username, password);
        quoteDao = new QuoteDao(c);
        httpHelper = new QuoteHttpHelper(apiKey, client);
        quoteService = new QuoteService(quoteDao, httpHelper);
    }

    @Test
    public void testSaveQuote() {
        Quote quote = quoteService.fetchQuoteDataFromAPI("MSFT").get();
        assertDoesNotThrow(()->{quoteService.save(quote);});
    }

    @Test
    public void testFindById() {
        boolean quoteFound = quoteService.findById("MSFT").isPresent();
        Assert.assertFalse(quoteFound);
    }

    @Test
    public void testFindBAll() {
        assertDoesNotThrow(()->{quoteService.findAll();});
    }

    @Test
    public void testQuoteIsNull() {
        Quote quote = null;
        assertThrows(NullPointerException.class, ()->{quoteService.save(quote);});
    }

    @Test
    public void testFindByIdNoSymbol() {
        assertThrows(IllegalArgumentException.class, ()->{quoteService.findById(null);});
    }

    @Test
    public void testDeleteByIdNoSymbol() {
        assertThrows(IllegalArgumentException.class, ()->{quoteService.deleteById(null);});
    }

    @Test
    public void testDeleteById() {

        assertDoesNotThrow(()->{quoteService.deleteById("MSFT");});
    }

    @Test
    public void testDeleteAll() {

        assertDoesNotThrow(()->{quoteService.deleteAll();});
    }

}

