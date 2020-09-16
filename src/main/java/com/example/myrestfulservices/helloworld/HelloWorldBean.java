package com.example.myrestfulservices.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloWorldBean {
    private final String message;

//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
