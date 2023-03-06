package com.epam.model;

import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Value
@Accessors(chain = true)
public class UserAccount {
    List<Currency> currencies;
}
