package com.task1.restapi.model;
import org.springframework.http.ResponseEntity;

import com.task1.restapi.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.task1.restapi.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository  customerRepository;

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        // Check if a customer with the same ID already exists
        Optional<Customer> existingCustomer = Optional.ofNullable(customerRepository.findById(customer.getId()));
        if (existingCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer with ID " + customer.getId() + " already exists.");
        }

        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully with id: " + customer.getId());
    }
    @GetMapping("/getCustomer")
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        boolean exists = customerRepository.existsById(id);
        Customer customer1 = customerRepository.findById(id);

        if(!exists){
            return "Customer does not exist";
        }
        customer1.setName(customer.getName());
        customer1.setSalary(customer.getSalary());
        customerRepository.save(customer1);
        return "Customer updated";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCustomer(@PathVariable int id){
        boolean exists = customerRepository.existsById(id);
        Customer customer1 = customerRepository.findById(id);

        if(!exists){
            return "Customer does not exist";
        }
        customerRepository.deleteById(id);
        return "Customer deleted";
    }

}
