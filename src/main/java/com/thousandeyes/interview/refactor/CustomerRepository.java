package com.thousandeyes.interview.refactor;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> customers = Map.of(
            "cust-42", new Customer("cust-42", "Ada Lovelace", "ada@example.com", 0.0725),
            "cust-84", new Customer("cust-84", "Grace Hopper", "grace@example.com", 0.065)
    );

    public Customer findById(String customerId) {
        return customers.getOrDefault(customerId, customers.get("cust-42"));
    }
}
