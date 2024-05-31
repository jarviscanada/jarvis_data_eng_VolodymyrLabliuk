package ca.jarvis.iex;

import org.springframework.web.server.ResponseStatusException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Api(value = "Trader", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/trader")
public class TraderAccountController {

    private TraderAccountService traderAccountService;

    @Autowired
    public TraderAccountController(TraderAccountService traderAccountService) {
        this.traderAccountService = traderAccountService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path = "/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public TraderAccountView createTrader (@PathVariable String firstname,
                                           @PathVariable String lastname,
                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob, @PathVariable String country, @PathVariable String email) {
        try {
            Trader trader = new Trader();
            trader.setFirstName(firstname);
            trader.setLastName (lastname);
            trader.setCountry(country); trader.setEmail(email);
            trader.setDob(Date.valueOf(dob));
            TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount (trader);
            return traderAccountView;
        } catch (Exception e) {
            //throw ResponseExceptionUtil.getResponseStatusException(e);
            throw e;
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public TraderAccountView createTrader (@RequestBody Trader trader) {
        try{
            return traderAccountService.createTraderAndAccount(trader);
        }catch (Exception e){
            //throw ResponseExceptionUtil.getResponseStatusException(e);
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(path = "/deposit/traderId/{traderId}/amount/{amount}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Account depositFund(@PathVariable Integer traderId, @PathVariable Double amount){
        try{
            return traderAccountService.deposit(traderId, amount);
        }catch (Exception e){
            //throw ResponseExceptionUtil.getResponseStatusException(e);
            throw e;
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/withdraw/traderId/{traderId}/amount/{amount}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Account withdrawFund(@PathVariable Integer traderId, @PathVariable Double amount){
        try{
            return traderAccountService.withdraw(traderId, amount);
        }catch (Exception e){
            //throw ResponseExceptionUtil.getResponseStatusException(e);
            throw e;
        }
    }

}


