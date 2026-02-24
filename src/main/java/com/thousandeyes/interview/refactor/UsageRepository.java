package com.thousandeyes.interview.refactor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UsageRepository {
    public Usage load(String customerId, LocalDate month) {
        return new Usage(customerId, month, List.of(
                new UsageMetric("api_calls", 900),
                new UsageMetric("data_gb", 125)
        ));
    }

    public void preload(String customerId) {
        // Pretend to warm caches or fetch from remote store.
    }
}
