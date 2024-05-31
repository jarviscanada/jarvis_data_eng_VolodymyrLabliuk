package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quote")
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

    @PutMapping("/iexMarketData")
    @ResponseStatus(HttpStatus.OK)
    public void saveQuote() {
        quoteService.updateMarketData();
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Quote putQuote(Quote quote) {
        return quoteService.saveQuote(quote);
    }

    @GetMapping("/dailyList")
    @ResponseStatus(HttpStatus.OK)
    public List<Quote> getDailyList() {
        return quoteService.findAllQuotes();
    }

    @PostMapping("/tickerId/{tickerId}")
    @ResponseStatus(HttpStatus.OK)
    public Quote createQuote(String tickerId) {
        return quoteService.saveQuote(tickerId);
    }
}
