package com.example.myrestfulservices.helloworld;

import com.example.myrestfulservices.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloWorldWebController {
//    @GetMapping("/test")
//    public String getUser() {
//        return "test";
//    }
//
//    @GetMapping("/createMemberForm")
//    public String createMemberForm() {
//        return "/members/createMemberForm";
//    }

    @GetMapping("/authResult")
    public String authResult(HttpServletRequest request) {
        Utils.DISPLAY_REQUEST_HEADER((request));
        return "Hi, there~";
    }
}
