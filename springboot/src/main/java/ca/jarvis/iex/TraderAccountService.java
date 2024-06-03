package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TraderAccountService {

    private final TraderDao traderDao;
    private final AccountDao accountDao;
    private final PositionDao positionDao;
    private final SecurityOrderDao securityOrderDao;

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    /**
     * Create a new trader and initialize a new account with 0 amount
     * - validate user input (all fields must be non empty)
     * - create a trader
     * - create an account
     * - create, setup, and return a new traderAccountView
     *
     * Assumption: to simplify the logic, each trader has only one account where traderId == accountId
     *
     * @param trader cannot be null. All fields cannot be null except for id (auto-generated by db)
     * @return traderAccountView
     * @throws IllegalArgumentException if a trader has null fields or id is not null
     */
    @Transactional
    public TraderAccountView createTraderAndAccount(Trader trader) {
        if (trader == null || trader.getId() != null || trader.getFirstName() == null || trader.getLastName() == null ||
                trader.getDob() == null || trader.getCountry() == null || trader.getEmail() == null) {
            throw new IllegalArgumentException("Invalid trader information");
        }

        Trader savedTrader = traderDao.save(trader);

        Account account = new Account();
        account.setAmount(0.0);
        account.setTrader(savedTrader);
        Account savedAccount = accountDao.save(account);

        return new TraderAccountView(savedTrader, savedAccount);
    }

    /**
     * A trader can be deleted if and only if it has no open position and 0 cash balance
     * - validate traderId
     * - get trader account by traderId and check account balance
     * - get positions by accountId and check positions
     * - delete all securityOrders, account, trader (in this order)
     *
     * @param traderId must not be null
     * @throws IllegalArgumentException if traderId is null or not found or unable to delete
     */
    @Transactional
    public void deleteTraderById(Integer traderId) {
        if (traderId == null) {
            throw new IllegalArgumentException("traderId cannot be null");
        }

        Trader trader = traderDao.findById(traderId)
                .orElseThrow(() -> new IllegalArgumentException("Trader not found"));
        Account account = accountDao.findByTraderId(traderId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getAmount() != 0.0) {
            throw new IllegalArgumentException("Cannot delete trader with non-zero account balance");
        }

        Position positions = positionDao.findByAccountId(account.getId()).get();
        if (positions.getPosition() > 0) {
            throw new IllegalArgumentException("Cannot delete trader with open positions");
        }

        securityOrderDao.deleteByAccountId(account.getId());
        accountDao.delete(account);
        traderDao.delete(trader);
    }

    /**
     * Deposit a fund to an account by traderId
     * - validate user input
     * - find account by trader id
     * - update the amount accordingly
     *
     * @param traderId must not be null
     * @param fund must be greater than 0
     * @return updated Account
     * @throws IllegalArgumentException if traderId is null or not found,
     *                                  and fund is less than or equal to 0
     */
    @Transactional
    public Account deposit(Integer traderId, Double fund) {
        if (traderId == null || fund == null || fund <= 0) {
            throw new IllegalArgumentException("Invalid traderId or fund");
        }

        Account account = accountDao.findByTraderId(traderId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.setAmount(account.getAmount() + fund);
        return accountDao.save(account);
    }

    /**
     * Withdraw a fund to an account by traderId
     * - validate user input
     * - find account by trader id
     * - update the amount accordingly
     *
     * @param traderId must not be null
     * @param fund must be greater than 0
     * @return updated Account
     * @throws IllegalArgumentException if traderId is null or not found,
     *                                  and fund is less than or equal to 0
     */
    @Transactional
    public Account withdraw(Integer traderId, Double fund) {
        if (traderId == null || fund == null || fund <= 0) {
            throw new IllegalArgumentException("Invalid traderId or fund");
        }

        Account account = accountDao.findByTraderId(traderId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getAmount() < fund) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setAmount(account.getAmount() - fund);
        return accountDao.save(account);
    }
}
