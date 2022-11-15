package com.example.myrestfulservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo")
//@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class UserV4 {
    Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
//    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    // 미래 데이터 사용 X
    @Past
//    @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요.")
    private Date joinDate;

//    @ApiModelProperty(notes = "사용자 비빌번호를 입력해 주세요.")
    private String password;
//    @ApiModelProperty(notes = "사용자 주민등록번호를 입력해 주세요.")
    private String ssn;
}