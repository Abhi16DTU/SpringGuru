package com.SpringGuru.DIpractice.service;

import org.springframework.stereotype.Service;

@Service
public class setterInjectedGreetingService implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Hello World - Setter";
    }

}
