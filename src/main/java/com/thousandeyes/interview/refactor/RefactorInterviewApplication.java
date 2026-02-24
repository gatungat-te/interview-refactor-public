package com.thousandeyes.interview.refactor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RefactorInterviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(RefactorInterviewApplication.class, args);
    }

    @Bean
    CommandLineRunner run(BillingCoordinator coordinator) {
        return args -> coordinator.runMonthlyBilling("cust-42");
    }
}
