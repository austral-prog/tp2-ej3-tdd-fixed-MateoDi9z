package com.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    public static Map<String, Integer> createAccount(Map<String, Integer> accounts, String name) {
        Map<String, Integer> newAccounts = new HashMap<>(accounts);
        newAccounts.put(name, 0);
        return newAccounts;
    }

    public static Map<String, Integer> deposit(Map<String, Integer> accounts, String name, int amount) {
        if (accounts.get(name) == null || amount <= 0) return accounts;

        Map<String, Integer> newAccounts = new HashMap<>();

        accounts.forEach((key, value) -> {
            if (Objects.equals(key, name)) newAccounts.put(key, value + amount);
            else newAccounts.put(key, value);
        });

        return newAccounts;
    }

    public static Map<String, Integer> withdraw(Map<String, Integer> accounts, String name, int amount) {
        if (accounts.get(name) == null || amount <= 0) return accounts;

        Map<String, Integer> newAccounts = new HashMap<>();

        accounts.forEach((key, value) -> {
            if (Objects.equals(key, name)) newAccounts.put(key, value - amount);
            else newAccounts.put(key, value);
        });

        return newAccounts;
    }

    public static Map<String, Integer> transfer(Map<String, Integer> accounts, String from, String to, int amount) {
        if (amount <= 0) return accounts;
        if (Objects.equals(from, to) || accounts.get(from) == null || accounts.get(to) == null) return accounts;
        if (accounts.get(from) - amount < 0) return accounts;

        Map<String, Integer> newAccounts = new HashMap<>();

        accounts.forEach((key, value) -> {
            if (Objects.equals(key, to)) newAccounts.put(key, value + amount);
            else if (Objects.equals(key, from)) newAccounts.put(key, value - amount);
            else newAccounts.put(key, value);
        });

        return newAccounts;
    }
}
