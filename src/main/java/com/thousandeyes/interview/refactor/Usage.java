package com.thousandeyes.interview.refactor;

import java.time.LocalDate;
import java.util.List;

public record Usage(String customerId, LocalDate month, List<UsageMetric> metrics) {
    public int totalUnits() {
        return metrics.stream().mapToInt(UsageMetric::quantity).sum();
    }
}
