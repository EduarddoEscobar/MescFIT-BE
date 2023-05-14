package com.mescfit.stripe.customer;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stripe/customers")
public class CustomerController {
    @Value("${app.stripe.secretKey}")
    private String secretKey;

    @GetMapping()
    public List<CustomerDTO> getAlLCustomers(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset, @RequestParam(required = false) String email) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit != null ? limit : 3);
        if (offset != null) {
            params.put("starting_after", offset);
        }

        if (email != null) {
            params.put("email", email);
        }

        CustomerCollection customers =
                Customer.list(params);
        return customers
                .getData()
                .stream()
                .map(customer -> new CustomerDTO(customer.getName(), customer.getEmail(), customer.getId()))
                .toList();
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable String customerId) throws StripeException {
        Stripe.apiKey = secretKey;
        Customer customer = Customer.retrieve(customerId);
        return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getId());
    }

    @PostMapping()
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) throws StripeException {
        Stripe.apiKey = secretKey;
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", customerDTO.name());
        params.put("email", customerDTO.email());
        Customer customer = Customer.create(params);
        return new CustomerDTO(customer);
    }

    @DeleteMapping("/{customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) throws StripeException {
        Stripe.apiKey = secretKey;
        Customer customer = Customer.retrieve(customerId);
        Customer deletedCustomer = customer.delete();
        return deletedCustomer.getDeleted();
    }
}
