package ca.jrvs.apps.jdbc;

public class StockQuoteController {

    private QuoteService quoteService;
    private PositionService positionService;

    StockQuoteController(QuoteService quoteService, PositionService positionService){
        this.quoteService = quoteService;
        this.positionService = positionService;
    }

    /**
     * User interface for our application
     */
    public void initClient() {
        //TO DO
    }

}
