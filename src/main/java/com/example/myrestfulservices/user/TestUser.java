package com.example.myrestfulservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data //lombok 사용으로 생성자,setter,getter 자동으로 생성됨
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password","ssn"}) //숨길 데이터를 제어하기 위한 어노테이션
//@JsonFilter("UserInfoV1") //Userinfo 라는 정보는 controler , service 에서 사용 가능하게됨
@NoArgsConstructor //디폴트생성자 생성
public class TestUser {
    private Integer id;
    @Size(min =2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String ssn;
}