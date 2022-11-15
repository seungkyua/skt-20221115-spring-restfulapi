package com.example.myrestfulservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data //lombok 사용으로 생성자,setter,getter 자동으로 생성됨
@NoArgsConstructor
@JsonFilter("UserInfoV2") //Userinfo 라는 정보는 controler , service 에서 사용 가능하게됨
class TestUserV2 extends User{
    private String grade;

}
