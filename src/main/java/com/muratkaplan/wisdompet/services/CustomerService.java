package com.muratkaplan.wisdompet.services;

import com.muratkaplan.wisdompet.data.entities.CustomerEntity;
import com.muratkaplan.wisdompet.data.repositories.CustomerRepository;
import com.muratkaplan.wisdompet.web.errors.NotFoundException;
import com.muratkaplan.wisdompet.web.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(String filterEmail){
       List<Customer> customers = new ArrayList<>();
       if(StringUtils.hasLength(filterEmail)){
         CustomerEntity entity=this.customerRepository.findByEmail(filterEmail);
         customers.add(this.translateDbToWeb(entity));
       }else{
              Iterable<CustomerEntity> entities = this.customerRepository.findAll();
              for(CustomerEntity entity:entities){
                customers.add(this.translateDbToWeb(entity));
              }
       }
         return customers;
    }
    public Customer getCustomerById(Long id){
        Optional<CustomerEntity> entity = this.customerRepository.findById(id);
        if(entity.isEmpty()){
            throw new NotFoundException("Customer not found with id: "+id);
        }
        return this.translateDbToWeb(entity.get());
    }
    public Customer createOrUpdateCustomer(Customer customer){
        CustomerEntity entity = this.translateWebToDb(customer);
        CustomerEntity savedEntity = this.customerRepository.save(entity);
        return this.translateDbToWeb(savedEntity);

    }

    public void deleteCustomerById(Long id){
        this.customerRepository.deleteById(id);
    }
    private CustomerEntity translateWebToDb(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());
        entity.setAddress(customer.getAddress());
        return entity;
    }
    private Customer translateDbToWeb(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress()
        );
    }
}
