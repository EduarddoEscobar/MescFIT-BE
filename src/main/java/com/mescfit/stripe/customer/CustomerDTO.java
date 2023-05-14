package com.mescfit.stripe.customer;

import com.stripe.model.Customer;

public record CustomerDTO(
        String name,
        String email,
        String customerId
) {
    public CustomerDTO(Customer customer) {
        this(customer.getName(), customer.getEmail(), customer.getId());
    }
}
