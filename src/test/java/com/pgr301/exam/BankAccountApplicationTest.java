package com.pgr301.exam;

import com.pgr301.exam.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankAccountApplicationTest {

        //Simple test that creates an account with an id, and checks if the id is correct 
        @Test
        void checkAccountHasCorrectId(){
            Account account = new Account();
            account.setId("1");
            assertEquals("1", account.getId());
        }
}
