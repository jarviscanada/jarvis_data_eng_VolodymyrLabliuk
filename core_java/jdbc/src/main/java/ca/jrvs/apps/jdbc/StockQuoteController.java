package ca.jrvs.apps.jdbc;

import java.util.Scanner;

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
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter ticker to display");
            String symbol = scanner.nextLine();
            System.out.println("Do you want to buy or sell?");
            String operation = scanner.nextLine();
            while(!operation.equals("buy") && !operation.equals("sell")){
                System.out.println("Invalid input, enter buy or sell");
                operation = scanner.nextLine();
            }
            Quote quote = quoteService.fetchQuoteDataFromAPI(symbol).get();
            String confirm = "";
            switch (operation){
                case "buy" :
                    System.out.println(quote);
                    System.out.println("Do you want buy shares with this quotes? Y or N");
                    confirm = scanner.nextLine().toUpperCase();
                    while(!confirm.equals("N") && !confirm.equals("Y")){
                        System.out.println("Invalid input, enter Y or N");
                        confirm = scanner.nextLine().toUpperCase();
                    }
                    if(confirm.equals("Y")) {
                        String numOfShares = scanner.nextLine();
                        int numOfSharesInt = Integer.parseInt(numOfShares);
                        quoteService.save(quote);
                        positionService.buy(symbol, numOfSharesInt, quote.getPrice());
                    }
                    break;
                case "sell" :
                    System.out.println("Do you want sell shares for " + quote.getPrice() + "$ ? Y or N");
                    confirm = scanner.nextLine().toUpperCase();
                    while(!confirm.equals("N") && !confirm.equals("Y")){
                        System.out.println("Invalid input, enter Y or N");
                        confirm = scanner.nextLine().toUpperCase();
                    }
                    if(confirm.equals("Y")) {
                        positionService.sell(symbol);
                    }
                    System.out.println("Quotes sold");
                    break;
            }
        }
    }

}
