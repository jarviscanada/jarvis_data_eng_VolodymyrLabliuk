package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final AccountDao accountDao;
    private final SecurityOrderDao securityOrderDao;
    private final QuoteDao quoteDao;
    private final PositionDao positionDao;

    @Autowired
    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao, PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.positionDao = positionDao;
    }

    @Transactional
    public SecurityOrder executeMarketOrder(MarketOrderDto orderData) {
        // Validate order size and ticker
        if (orderData.getSize() <= 0 || orderData.getTicker() == null || orderData.getTicker().isEmpty()) {
            throw new IllegalArgumentException("Invalid order size or ticker");
        }


        // Create a new SecurityOrder
        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.getAccount().setId(orderData.getAccountId());
        securityOrder.setStatus("PENDING");
        securityOrder.setTicker(orderData.getTicker());
        securityOrder.setSize(orderData.getSize());
        securityOrder.setPrice(quoteDao.findQuoteByTicker(orderData.getTicker()).get().getLastPrice());

        Account account = accountDao.findById(orderData.getAccountId()).get();

        // Handle buy or sell orders
        if (orderData.getSize() > 0) {
            handleBuyMarketOrder(orderData, securityOrder, account);
        } else {
            handleSellMarketOrder(orderData, securityOrder, account);
        }

        // Save and return the securityOrder
        securityOrder = securityOrderDao.save(securityOrder);
        return securityOrder;
    }

    protected void handleBuyMarketOrder(MarketOrderDto marketOrder, SecurityOrder securityOrder, Account account) {
        double orderAmount = marketOrder.getSize() * securityOrder.getPrice();
        if (account.getAmount() >= orderAmount) {
            account.setAmount(account.getAmount() - orderAmount);
            accountDao.save(account);

            securityOrder.setStatus("FILLED");
        } else {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Insufficient funds");
        }
    }

    protected void handleSellMarketOrder(MarketOrderDto marketOrder, SecurityOrder securityOrder, Account account) {
        Position position = positionDao.findByAccountIdAndTicker(account.getId(), marketOrder.getTicker());
        if (position != null && position.getPosition() >= Math.abs(marketOrder.getSize())) {
            double orderAmount = Math.abs(marketOrder.getSize()) * securityOrder.getPrice();
            account.setAmount(account.getAmount() + orderAmount);
            accountDao.save(account);

            securityOrder.setStatus("FILLED");
        } else {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Insufficient position");
        }
    }
}

