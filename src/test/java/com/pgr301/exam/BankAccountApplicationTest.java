package com.pgr301.exam;

import com.pgr301.exam.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankAccountApplicationTest {


        @Test
        void checkAccountHasCorrectId(){
            Account account = new Account();
            account.setId("3");
            assertEquals("1", account.getId());
        }
}
