package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/marketOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityOrder postMarketOrder(MarketOrder marketOrder) {
        MarketOrderDto marketOrderDto = new MarketOrderDto(marketOrder.getAccountId(), marketOrder.getTicker(), marketOrder.getSize());
        return orderService.executeMarketOrder(marketOrderDto);
    }

}
