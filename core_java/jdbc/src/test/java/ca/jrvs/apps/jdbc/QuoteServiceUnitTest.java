package ca.jrvs.apps.jdbc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class QuoteServiceUnitTest {
    private QuoteDao quoteDao;
    private QuoteService quoteService;
    private QuoteHttpHelper httpHelper;

    @Before
    public void setUp(){
        quoteDao = mock(QuoteDao.class);
        httpHelper = mock(QuoteHttpHelper.class);
        quoteService = new QuoteService(quoteDao, httpHelper);
    }

    @Test
    public void testSaveQuote() {
        Quote quote = new Quote("MSFT", 332.3800, 333.8300, 326.3600,
                327.7300, 21085695,new Date(2023-10-13),
                331.1600, -3.4300, "-1.0358%", new Timestamp(System.currentTimeMillis()));
        when(quoteDao.save(quote)).thenReturn(quote);

        Quote savedQuote = quoteService.save(quote);

        assertNotNull(savedQuote);
        assertEquals(quote, savedQuote);

        verify(quoteDao, times(1)).save(quote);
    }

    @Test
    public void testFindQuoteById() {
        Quote testQuote = new Quote("AAPL", 150.0, 160.0, 145.0, 155.0, 10000,
                new Date(System.currentTimeMillis()), 150.0, 5.0, "3.33%", new Timestamp(System.currentTimeMillis()));
        String symbol = "MSFT";

        when(quoteDao.findById(symbol)).thenReturn(Optional.of(testQuote));
        Quote returnedQuote = quoteService.findById(symbol).get();
        assertEquals(testQuote, returnedQuote);
    }

    @Test
    public void testFindAllQuotes() {
        List<Quote> testQuotes = Arrays.asList(
                new Quote("AAPL", 150.0, 160.0, 145.0, 155.0, 10000,
                        new Date(System.currentTimeMillis()), 150.0, 5.0, "3.33%", new Timestamp(System.currentTimeMillis())),
                new Quote("GOOGL", 2500.0, 2550.0, 2450.0, 2520.0, 20000,
                        new Date(System.currentTimeMillis()), 2500.0, 20.0, "0.8%", new Timestamp(System.currentTimeMillis()))
        );
        when(quoteDao.findAll()).thenReturn(testQuotes);
        Iterable<Quote> returnedQuotes = quoteService.findAll();
        assertEquals(testQuotes, returnedQuotes);
    }

    @Test
    public void testDeleteQuoteById() {
        String symbol = "MSFT";
        quoteService.deleteById(symbol);
        verify(quoteDao, times(1)).deleteById(symbol);
    }

    @Test
    public void testDeleteAllQuotes() {
        quoteService.deleteAll();
        verify(quoteDao, times(1)).deleteAll();
    }
}

