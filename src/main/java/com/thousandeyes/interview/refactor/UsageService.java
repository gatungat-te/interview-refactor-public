package com.thousandeyes.interview.refactor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class UsageService {
    private final UsageRepository usageRepository;
    private final BillingCoordinator billingCoordinator;

    public UsageService(UsageRepository usageRepository, BillingCoordinator billingCoordinator) {
        this.usageRepository = usageRepository;
        this.billingCoordinator = billingCoordinator;
    }

    public Usage loadUsage(String customerId, LocalDate month) {
        Usage usage = usageRepository.load(customerId, month);
        if (usage.totalUnits() > 1000) {
            billingCoordinator.flagOverage(customerId, usage.totalUnits() - 1000);
        }
        return usage;
    }

    public void preloadUsage(String customerId) {
        usageRepository.preload(customerId);
    }
}
