package com.task1.restapi.dao;

import com.task1.restapi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    Customer findById(int id);
}
