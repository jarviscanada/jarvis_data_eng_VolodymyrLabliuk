import ca.jarvis.iex.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public MarketDataDao marketDataDao() {
        return Mockito.mock(MarketDataDao.class);
    }

    @Bean
    @Primary
    public QuoteDao quoteDao() {
        return Mockito.mock(QuoteDao.class);
    }

    @Bean
    @Primary
    public AccountDao accountDao() {
        return Mockito.mock(AccountDao.class);
    }

    @Bean
    @Primary
    public TraderDao traderDao() {
        return Mockito.mock(TraderDao.class);
    }

    @Bean
    @Primary
    public QuoteService quoteService(MarketDataDao marketDataDao, QuoteDao quoteDao) {
        return new QuoteService(marketDataDao, quoteDao);
    }
}

