package com.thousandeyes.interview.refactor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Invoice(
        String id,
        String customerId,
        LocalDate issuedOn,
        List<LineItem> lineItems,
        BigDecimal subtotal,
        BigDecimal discount,
        BigDecimal tax,
        BigDecimal total
) {}
