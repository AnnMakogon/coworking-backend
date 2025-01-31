package dev.coworking.controller;

import dev.coworking.dto.Customer;
import dev.coworking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @PutMapping(value = "customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateCustomer(@RequestBody Customer changingCustomer) {
        return customerService.updateCustomer(changingCustomer);
    }

    @GetMapping(value = "customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer getPersInfo(@PathVariable("id") Long id) {
        return customerService.getPersInfo(id);
    }
}
