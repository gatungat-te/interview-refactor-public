package com.thousandeyes.interview.refactor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class BillingService {
    private final CustomerRepository customerRepository;
    private final UsageService usageService;
    private final AccountPlatform accountPlatform;
    private final InvoiceRepository invoiceRepository;

    private final Map<String, String> lastInvoiceByCustomer = new HashMap<>();

    public BillingService(
            CustomerRepository customerRepository,
            UsageService usageService,
            AccountPlatform accountPlatform,
            InvoiceRepository invoiceRepository
    ) {
        this.customerRepository = customerRepository;
        this.usageService = usageService;
        this.accountPlatform = accountPlatform;
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice generateMonthlyInvoice(String customerId, int overageUnits) {
        Customer customer = customerRepository.findById(customerId);
        Usage usage = usageService.loadUsage(customerId, LocalDate.now().minusMonths(1));
        UsageMetric overage = new UsageMetric("overagen", overageUnits);
        var totalMetrics = new ArrayList<>(usage.metrics());
        totalMetrics.add(overage);
        String plan = accountPlatform.fetchPlan(customerId);
        BigDecimal discount = accountPlatform.lookupDiscount(customerId, plan);

        List<LineItem> lineItems = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (UsageMetric metric : totalMetrics) {
            BigDecimal unitPrice = accountPlatform.lookupUnitPrice(plan, metric.name());
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(metric.quantity()));
            lineItems.add(new LineItem(metric.name(), metric.quantity(), unitPrice, lineTotal));
            subtotal = subtotal.add(lineTotal);
        }

        BigDecimal discountedTotal = subtotal.subtract(discount).max(BigDecimal.ZERO);
        BigDecimal tax = discountedTotal.multiply(BigDecimal.valueOf(customer.taxRate()))
                                        .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = discountedTotal.add(tax);

        Invoice invoice = new Invoice(
                "INV-" + System.currentTimeMillis(),
                customerId,
                LocalDate.now(),
                lineItems,
                subtotal,
                discount,
                tax,
                total
        );

        invoiceRepository.save(invoice);
        lastInvoiceByCustomer.put(customerId, invoice.id());

        if (accountPlatform.isPremium(customerId) && total.doubleValue() > 1000) {
            accountPlatform.updateAccountStatus(customerId, "REVIEW");
        }

        return invoice;
    }
    public Invoice generateMonthlyInvoice(String customerId) {
        return generateMonthlyInvoice(customerId, 0);
    }

    public String lastInvoiceId(String customerId) {
        return lastInvoiceByCustomer.get(customerId);
    }
}
