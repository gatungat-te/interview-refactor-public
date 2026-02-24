package com.thousandeyes.interview.refactor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepository {
    private final List<Invoice> invoices = new ArrayList<>();

    public void save(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> findAll() {
        return List.copyOf(invoices);
    }
}
