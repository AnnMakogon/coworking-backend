package dev.coworking.service;

import dev.coworking.dto.Customer;
import dev.coworking.mapper.CustomerMapper;
import dev.coworking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public Customer getPersInfo(Long id){
        return customerMapper.customerEntityToCustomer(
                customerRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException( HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @Transactional
    public Customer updateCustomer(Customer changingCustomer){
        customerRepository.save(
                customerMapper.customerToCustomerEntity(changingCustomer));
        return changingCustomer;
    }


}
