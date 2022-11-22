package com.example.jpa.repository;

import com.example.jpa.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}