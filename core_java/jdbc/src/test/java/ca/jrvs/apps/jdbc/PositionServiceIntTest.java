package ca.jrvs.apps.jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PositionServiceIntTest {

    private PositionDao positionDao;
    private PositionService positionService;
    private Connection c;
    private String url = "jdbc:postgresql://localhost:5432/stock_quote";
    private String username = "postgres";
    private String password = "password";

    @Before
    public void setup() throws SQLException {
        c = DriverManager.getConnection(url, username, password);
        positionDao = new PositionDao(c);
        positionService = new PositionService(positionDao);
    }

    @Test
    public void testBuyPosition() {
        assertDoesNotThrow(()->{positionService.buy("MSFT", 100, 1500);});
    }

    @Test
    public void testSellPosition() {
        assertDoesNotThrow(()->{positionService.sell("MSFT");});
    }

}
