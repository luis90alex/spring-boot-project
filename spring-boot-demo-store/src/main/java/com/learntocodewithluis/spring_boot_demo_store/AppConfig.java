package com.learntocodewithluis.spring_boot_demo_store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${app.payment-gateway:stripe}")
    private String paymentGateway;
    private static final String PAYMENT_GATEWAY ="stripe";

    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal(){
        return new PaypalPaymentService();
    }

    @Bean
    public OrderService orderService(){
        return PAYMENT_GATEWAY.equalsIgnoreCase(paymentGateway) ?
                new OrderService(stripe()) : new OrderService(paypal());
    }

}
