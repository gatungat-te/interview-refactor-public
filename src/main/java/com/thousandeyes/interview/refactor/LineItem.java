package com.thousandeyes.interview.refactor;

import java.math.BigDecimal;

public record LineItem(String metric, int quantity, BigDecimal unitPrice, BigDecimal total) {}
