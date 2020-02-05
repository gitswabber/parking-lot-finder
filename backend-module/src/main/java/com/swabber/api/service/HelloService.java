package com.swabber.api.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getHelloMessage() {
        return "Hello, Spring boot!";
    }
}
