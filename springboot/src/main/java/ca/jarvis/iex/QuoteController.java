package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    private final QuoteService quoteService;

    @Value("${app.init.dailyList}")
    private String[] initDailyList;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/iex/ticker/{ticker}")
    public IexQuote getQuote(@PathVariable String ticker) throws Exception {
        return quoteService.getQuote(ticker);
    }

//    @GetMapping("/iex/tickers")
//    public List<IexQuote> getQuotes() throws Exception {
//        return quoteService.getQuotes(Arrays.asList(initDailyList));
//    }

    @PostMapping("/iexMarketData ")
    public ResponseEntity<Void> saveQuote(@RequestBody IexQuote quote) {
        quoteService.updateMarketData();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
