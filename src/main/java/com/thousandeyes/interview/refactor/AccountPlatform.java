package com.thousandeyes.interview.refactor;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AccountPlatform {
    public String fetchPlan(String customerId) {
        return "standard";
    }

    public BigDecimal lookupDiscount(String customerId, String plan) {
        return "standard".equals(plan) ? BigDecimal.TEN : BigDecimal.ZERO;
    }

    public BigDecimal lookupUnitPrice(String plan, String metric) {
        if ("api_calls".equals(metric)) {
            return BigDecimal.valueOf(0.02);
        }
        return BigDecimal.valueOf(1.5);
    }

    public boolean isPremium(String customerId) {
        return "cust-84".equals(customerId);
    }

    public Map<String, String> loadFeatureFlags(String customerId) {
        return Map.of("betaDiscounts", "true", "invoicePdf", "false");
    }

    public void updateAccountStatus(String customerId, String status) {
        // Placeholder for remote update.
    }
}
