package ca.jrvs.apps.jdbc;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PositionServiceUnitTest {

    PositionDao positionDao;
    PositionService positionService;

    @Before
    public void setUp(){
        positionDao = mock(PositionDao.class);
        positionService = new PositionService(positionDao);
    }

    @Test
    public void testBuyPosition() {
        String ticker = "AAPL";
        int numberOfShares = 10;
        double price = 150.0;

        when(positionDao.findById(ticker)).thenReturn(Optional.empty());

        Position savedPosition = new Position();
        savedPosition.setTicker(ticker);
        savedPosition.setNumOfShares(numberOfShares);
        savedPosition.setValuePaid(price);

        when(positionDao.save(any(Position.class))).thenReturn(savedPosition);

        Position boughtPosition = positionService.buy(ticker, numberOfShares, price);

        assertNotNull(boughtPosition);
        assertEquals(ticker, boughtPosition.getTicker());
        assertEquals(numberOfShares, boughtPosition.getNumOfShares());
        assertEquals(price, boughtPosition.getValuePaid(), 0.01);

        verify(positionDao, times(1)).save(any(Position.class));

    }
}
