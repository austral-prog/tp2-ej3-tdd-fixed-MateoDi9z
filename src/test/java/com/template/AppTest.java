package com.template;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.template.App.createAccount;
import static com.template.App.deposit;
import static com.template.App.withdraw;
import static com.template.App.transfer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    public void testAccountCreation() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);
        Map<String, Integer> updatedAccounts = createAccount(accounts, "bruno");

        assertEquals(3, updatedAccounts.size());
        assertEquals(0, updatedAccounts.get("bruno"));
    }

    @Test
    public void testDeposit() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts = deposit(accounts, "mati", 5);
        assertEquals(15, updatedAccounts.get("mati"));
    }

    @Test
    public void testDepositNotFound() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts3 = deposit(accounts, "BRUNO", 5);
        assertEquals(2, updatedAccounts3.size());
    }

    @Test
    public void testDepositNeg() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts2 = deposit(accounts, "mati", -5);
        assertEquals(10, updatedAccounts2.get("mati"));
    }

    @Test
    public void testWithdraw() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts = withdraw(accounts, "mati", 5);
        assertEquals(5, updatedAccounts.get("mati"));
    }

    @Test
    public void testWithdrawNeg() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts1 = withdraw(accounts, "mati", -5);
        assertEquals(10, updatedAccounts1.get("mati"));
    }

    @Test
    public void testWithdrawNotFound() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts = withdraw(accounts, "TTD", 20);
        assertEquals(20, updatedAccounts.get("juan"));
        assertEquals(10, updatedAccounts.get("mati"));
        assertEquals(accounts.size(), updatedAccounts.size());
    }

    @Test
    public void testTransfer() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        // Ok
        Map<String, Integer> updatedAccounts = transfer(accounts, "mati", "juan", 10);

        assertEquals(0, updatedAccounts.get("mati"));
        assertEquals(30, updatedAccounts.get("juan"));
    }

    @Test
    public void testTransferNeg() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);
        Map<String, Integer> updatedAccounts = transfer(accounts, "mati", "juan", -10);

        assertEquals(10, updatedAccounts.get("mati"));
        assertEquals(20, updatedAccounts.get("juan"));
    }

    @Test
    public void testTransferZeroBalance() {
        Map<String, Integer> accounts = Map.of("mati", 0, "juan", 30);
        Map<String, Integer> updatedAccounts = transfer(accounts, "mati", "juan", 10);

        assertEquals(0, updatedAccounts.get("mati"));
        assertEquals(30, updatedAccounts.get("juan"));
    }

    @Test
    public void testTransferNotFound() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts2 = transfer(accounts, "BRUNO777", "juan", 10);
        Map<String, Integer> updatedAccounts3 = transfer(accounts, "juan", "BRUNO777", 10);

        assertEquals(20, updatedAccounts2.get("juan"));
        assertEquals(20, updatedAccounts3.get("juan"));
    }

    @Test
    public void testTransferFromSameAccount() {
        Map<String, Integer> accounts = Map.of("mati", 10, "juan", 20);

        Map<String, Integer> updatedAccounts = transfer(accounts, "juan", "juan", 10);
        assertEquals(20, updatedAccounts.get("juan"));
    }
}