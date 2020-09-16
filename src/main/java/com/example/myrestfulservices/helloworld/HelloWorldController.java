package com.example.myrestfulservices.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        log.debug("Hello World");
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable(value = "name") String name) {
        return new HelloWorldBean(String.format("Hello World Bean, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }



    @GetMapping(path = "/test-world-internationalized")
    public String testWorldInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println(locale.getLanguage());
        return messageSource.getMessage("greeting.message", null, locale);
    }

    @GetMapping(path = "/hi-world-internationalized")
    public String hiWorldInternationalized() {
        return messageSource.getMessage("greeting.message", null, LocaleContextHolder.getLocale());
    }
}
