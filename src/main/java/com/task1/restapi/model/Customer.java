package com.task1.restapi.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data


public class Customer {
    @Id
    private int id;
    private String name;
    private long salary;
}
