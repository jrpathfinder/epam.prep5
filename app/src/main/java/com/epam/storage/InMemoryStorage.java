package com.epam.storage;

import com.epam.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InMemoryStorage {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<UserAccount> accounts;

    public static void init() throws IOException {
        UserAccount userAccount = objectMapper.readValue(new File("app/src/main/resources/useraccounts/account1.json"), UserAccount.class);
        accounts.add(userAccount);
    }
}
