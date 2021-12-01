package com.pgr301.exam;

import com.pgr301.exam.model.Account;
import com.pgr301.exam.model.Transaction;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static java.util.Optional.ofNullable;

@RestController
public class BankAccountController implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private BankingCoreSystmeService bankService;

    @Autowired
    private MeterRegistry meterRegistry;

    private Timer timer;

    @Autowired
    public BankAccountController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.timer = meterRegistry.timer("app.timer", "type", "ping");
    }

    long start = System.currentTimeMillis();

    @PostMapping(path = "/account/{fromAccount}/transfer/{toAccount}", consumes = "application/json", produces = "application/json")
    public void transfer(@RequestBody Transaction tx, @PathVariable String fromAccount, @PathVariable String toAccount) {
        try {
            bankService.transfer(tx, fromAccount, toAccount);
        }catch (BackEndException e){
            meterRegistry.counter("backend_exception", "db", "users").increment();
        }
        timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
    }

    @PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> updateAccount(@RequestBody Account a) {
        try {
            bankService.updateAccount(a);
        }catch (BackEndException e){
            meterRegistry.counter("backend_exception").increment();
        }
        timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @GetMapping(path = "/account/{accountId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> balance(@PathVariable String accountId) {
        Account account = null;
        try {
            account = ofNullable(bankService.getAccount(accountId)).orElseThrow(AccountNotFoundException::new);
        } catch (BackEndException e) {
            meterRegistry.counter("backend_exception").increment();
        }
        timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "video not found")
    public static class AccountNotFoundException extends RuntimeException {
    }
}

