package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.models.Account;

import java.time.LocalDate;
import java.util.HashSet;

public class UtilForTest {
    public static Account initAccount() {
        Account account = new Account("Sergey", "Rabotyaga", "Olegovich",
                LocalDate.of(1991, 3, 12), new HashSet<>(), new HashSet<>(), "Sergey home address", "Sergey work address",
                "insert@mail.ru", "124423", "sergey@live.com", "like sun", "12345", null);

        return account;
    }
}
