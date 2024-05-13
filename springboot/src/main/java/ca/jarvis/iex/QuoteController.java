package ca.jarvis.iex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/iex/tickers")
    public List<IexQuote> getQuotes() throws Exception {
        return quoteService.getQuotes(Arrays.asList(initDailyList));
    }
}
