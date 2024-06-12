import ca.jarvis.iex.iexQuote.IexQuote;
import ca.jarvis.iex.quote.Quote;
import ca.jarvis.iex.quote.QuoteDao;
import ca.jarvis.iex.quote.QuoteService;
import ca.jarvis.iex.utils.MarketDataDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestConfig.class})
public class QuoteService_UnitTest {
    @Mock
    private MarketDataDao marketDataDao;

    @Mock
    private QuoteDao quoteDao;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void testFindIexQuoteByTicker() throws IOException {
        IexQuote iexQuote = new IexQuote();
        iexQuote.setSymbol("AAPL");
        iexQuote.setLatestPrice(150.00);
        when(marketDataDao.findById("AAPL")).thenReturn(Optional.of(iexQuote));
        IexQuote foundQuote = marketDataDao.findById("AAPL").get();
        assertNotNull(foundQuote);
        assertEquals("AAPL", foundQuote.getSymbol());
        assertEquals(150.00, foundQuote.getLatestPrice(), 0.001);
    }

    @Test
    public void testUpdateMarketData() {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote());
        when(quoteDao.findAll()).thenReturn(quotes);
        quoteService.updateMarketData();
        verify(quoteDao).saveAll(anyList());
    }

    @Test
    public void testGetQuote() throws Exception {
        IexQuote mockIexQuote = new IexQuote();
        mockIexQuote.setSymbol("AAPL");
        mockIexQuote.setLatestPrice(150.00);
        when(marketDataDao.findById("AAPL")).thenReturn(Optional.of(mockIexQuote));
        IexQuote result = quoteService.getQuote("AAPL");
        assertEquals("AAPL", result.getSymbol());
        assertEquals(150.00, result.getLatestPrice(), 0.001);
    }

}
