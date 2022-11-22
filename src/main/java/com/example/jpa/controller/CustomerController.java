package com.example.jpa.controller;

import com.example.jpa.model.entity.Customer;
import com.example.jpa.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
record CustomerRestController(CustomerRepository repository) {

    @GetMapping("/customers")
    Collection<Customer> customers() {
        return this.repository.findAll();
    }
}