package com.thousandeyes.interview.refactor;

import org.springframework.stereotype.Service;

@Service
public class BillingCoordinator {
    private final UsageService usageService;
    private final BillingService billingService;

    public BillingCoordinator(UsageService usageService, BillingService billingService) {
        this.usageService = usageService;
        this.billingService = billingService;
    }

    public void runMonthlyBilling(String customerId) {
        usageService.preloadUsage(customerId);
        billingService.generateMonthlyInvoice(customerId);
    }

    public void flagOverage(String customerId, int overageUnits) {
        billingService.generateMonthlyInvoice(customerId, overageUnits);
    }
}
