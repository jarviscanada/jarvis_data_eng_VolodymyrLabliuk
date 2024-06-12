import ca.jarvis.iex.account.Account;
import ca.jarvis.iex.account.AccountDao;
import ca.jarvis.iex.trader.Trader;
import ca.jarvis.iex.trader.TraderDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Trader trader = new Trader();
        trader.setFirstName("John");
        trader.setLastName("Doe");
        trader.setDob(new Date());
        trader.setCountry("USA");
        trader.setEmail("john.doe@example.com");

        Trader savedTrader = traderDao.save(trader);
        assertNotNull(savedTrader.getId(), "Trader ID should not be null");

        Account account = new Account();
        account.setTrader(savedTrader);
        account.setAmount(0.0);

        Account savedAccount = accountDao.save(account);
        assertNotNull(savedAccount.getId(), "Account ID should not be null");

        System.out.println("Trader ID: " + savedTrader.getId());
        System.out.println("Account ID: " + savedAccount.getId());
    }
}
