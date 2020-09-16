package com.example.myrestfulservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class MyRestfulServicesApplication {

    public static void main(String[] args) {
        ApplicationContext application = SpringApplication.run(MyRestfulServicesApplication.class, args);

//        for (String str : application.getBeanDefinitionNames()) {
//            System.out.println(str);
//        }
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);      // <---- 해당 값을 수정하여 언어 결정
        return sessionLocaleResolver;
    }

//    @Bean
//    public ResourceBundleMessageSource buildMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
}
