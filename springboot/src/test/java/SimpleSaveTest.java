import ca.jarvis.iex.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class SimpleSaveTest {

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private AccountDao accountDao;

    @Test
    //@Transactional
    public void testSaveTraderAndAccount() {
        // Create and save a trader
        Trader trader = new Trader();
        trader.setFirstName("John");
        trader.setLastName("Doe");
        trader.setDob(new Date());
        trader.setCountry("USA");
        trader.setEmail("john.doe@example.com");

        Trader savedTrader = traderDao.save(trader);
        assertNotNull(savedTrader.getId(), "Trader ID should not be null");

        // Create and save an account
        Account account = new Account();
        account.setTrader(savedTrader);
        account.setAmount(0.0);

        Account savedAccount = accountDao.save(account);
        assertNotNull(savedAccount.getId(), "Account ID should not be null");

        System.out.println("Trader ID: " + savedTrader.getId());
        System.out.println("Account ID: " + savedAccount.getId());
    }
}
