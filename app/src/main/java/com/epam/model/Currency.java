package com.epam.model;

import lombok.Value;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Value
@Accessors(chain = true)
public class Currency {
    String name;
    BigDecimal value;
}
