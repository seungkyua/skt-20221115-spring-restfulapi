package com.example.myrestfulservices.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin2") //공통적인 앞의 prefix가 붙는다면 클래스단에 선언하여 적용 가능
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    //GET /admin/users/1 or /users/9 -> String으로 전달됨
    //-> /admin/v1/users/1
    //@GetMapping("/v1/users/{id}") //uri를 이용한 버전관리
    //@GetMapping(value = "/users/{id}",params = "version=1") //Requst Param으로 관리하느방법
    //@GetMapping(value ="/users/{id}",headers = "X-API-VERSION=1")
    @GetMapping(value ="/users/{id}",produces = "application/vnd.company.appv1+json")//mimetime을 이용하는 방법
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){ //int로 자동으로 변환됨
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn","password","grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV1",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value ="/users/{id}",produces = "application/vnd.company.appv3+json")//mimetime을 이용하는 방법
    public TestUser retrieveUserV3(@PathVariable int id){ //int로 자동으로 변환됨
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        TestUser testUser = new TestUser();
        BeanUtils.copyProperties(user, testUser);

        return testUser;
    }

    //@GetMapping("/v2/users/{id}") //uri를 이용한 버전관리
    //@GetMapping(value = "/users/{id}",params = "version=2") //Requst Param으로 관리하느방법
    //@GetMapping(value ="/users/{id}",headers = "X-API-VERSION=2") //header 값으로 관리하는 방법
    @GetMapping(value ="/users/{id}",produces = "application/vnd.company.appv2+json") //
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){ //int로 자동으로 변환됨
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        // User -> User2
        TestUserV2 userV2 = new TestUserV2();
        BeanUtils.copyProperties(user,userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","ssn","grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }

}