package com.example.myrestfulservices.utils;

import com.example.myrestfulservices.user.User;
import lombok.Data;
//import org.springframework.hateoas.RepresentationModel;

@Data
public class UserModel { // extends RepresentationModel<UserModel> {
    int id;
    String name;
}
