package com.swabber.api.controller;

import com.swabber.api.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return helloService.getHelloMessage();
    }
}
