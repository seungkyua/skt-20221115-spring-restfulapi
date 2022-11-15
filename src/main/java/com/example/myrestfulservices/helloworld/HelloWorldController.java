package com.example.myrestfulservices.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;
import java.util.Locale;

@RestController
@Slf4j
public class HelloWorldController {
    private MessageSource messageSource;
    private LocaleResolver resolver;

    @Autowired
    public HelloWorldController(MessageSource messageSource, LocaleResolver resolver) {
        this.messageSource = messageSource;
        this.resolver = resolver;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String hostname = InetAddress.getLocalHost().getHostName();

        log.debug("Local host address: {}", InetAddress.getLocalHost().getHostAddress());
        log.debug("Local host name: {}", InetAddress.getLocalHost().getHostName());

        return String.format("Hello World, %s, %s, %s", ip, hostname, new Date(System.currentTimeMillis()));
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
    public String testWorldInternationalizedWithoutHeader(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        System.out.println(acceptLanguage);
        Locale locale = resolver.resolveLocale(request);
        return messageSource.getMessage("greeting.message", null, locale);
    }

    @GetMapping(path = "/hi-world-internationalized")
    public String hiWorldInternationalized() {
        return messageSource.getMessage("greeting.message", null, LocaleContextHolder.getLocale());
    }
}
