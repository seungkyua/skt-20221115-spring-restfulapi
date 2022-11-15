package com.example.myrestfulservices.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/test")
public class UserControllerDemo {
    @Autowired
    private  UserDaoServiceDemo service;

    // public UserController(UserDaoService service) {
    //     this.service = service;
    // }

    @GetMapping("/users")
    public List<EntityModel<UserV4>> retrieveAllUsers() {
        List<EntityModel<UserV4>> models = new ArrayList<>();
        List<UserV4> users = service.findAll();

        // HATEOAS
        for (UserV4 user : users) {
            EntityModel model = EntityModel.of(user);
            model.add(linkTo(methodOn(this.getClass()).retrieveUser(user.getId())).withRel("detail"));
            models.add(model);
        }

        // JacksonFilter
        // SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        //         .filterOutAllExcept("id", "name", "joinDate");
        // FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        //
        // MappingJacksonValue mapping = new MappingJacksonValue(models);
        // mapping.setFilters(filters);

        return models;
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) {
        UserV4 user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }

        // HATEOAS
        EntityModel<UserV4> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        // JacksonFilter
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(model);
        mapping.setFilters(filters);
//        mapping.setSerializationView(Views.Public.class);

//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.wri

        return mapping;
    }

    @PostMapping("/users")
    public ResponseEntity<UserV4> createUser(@Valid @RequestBody UserV4 user) {
        UserV4 savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        UserV4 user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }
    }

}
