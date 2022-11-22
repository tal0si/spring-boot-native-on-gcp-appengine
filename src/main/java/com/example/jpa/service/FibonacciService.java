package com.example.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FibonacciService {
    public String fibonacci(int count) {
        int nbr1, nbr2, nbr3, i;
        nbr1 = 0;
        nbr2 = 1;
        StringBuffer output = new StringBuffer("");
        output.append(nbr1);
        output.append(" ");
        output.append(nbr2);

        for(i = 2; i < count; ++i)
        {
            nbr3 = nbr1 + nbr2;
            nbr1 = nbr2;
            nbr2 = nbr3;
            output.append(" ");
            output.append(nbr3);
        }
        String result = output.toString();
        return result;
    }
}
