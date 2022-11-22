package com.example.jpa.controller;

import com.example.jpa.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FibonacciRestController {

    @Autowired
    private FibonacciService fibonacciService;
    @GetMapping(path = "/fibonacci/{count}")
    public String fibonacci(@PathVariable("count") int count) {
        return fibonacciService.fibonacci(count);
    }

}