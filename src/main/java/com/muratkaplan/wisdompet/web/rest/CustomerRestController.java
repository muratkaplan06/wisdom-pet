package com.muratkaplan.wisdompet.web.rest;

import com.muratkaplan.wisdompet.services.CustomerService;
import com.muratkaplan.wisdompet.web.errors.BadRequestException;
import com.muratkaplan.wisdompet.web.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll(@RequestParam(name="email",required = false) String email){
        return customerService.getAllCustomers(email);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer){
        return customerService.createOrUpdateCustomer(customer);
    }
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        if(!id.equals(customer.getCustomerId())){
            throw new BadRequestException("Customer id does not match");
        }
        customer.setCustomerId(id);
        return customerService.createOrUpdateCustomer(customer);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
    }
}
