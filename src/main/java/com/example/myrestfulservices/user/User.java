package com.example.myrestfulservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
//@JsonFilter("UserInfo")
@NoArgsConstructor
//@ApiModel(description = "사용자 상세 정보")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "users")
public class User {
    @JsonIgnore
    String address;

    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
//    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;

    @Past
//    @ApiModelProperty(notes = "등록일을 입력해 주세요.")
    private Date joinDate;

//    @ApiModelProperty(notes = "비밀번호를 입력해 주세요.")
    private String password;

//    @ApiModelProperty(notes = "주민등록번호를 입력해 주세요.")
    private String ssn;

//    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.") String name, @Past Date joinDate,
                String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }

    public User(Integer id, @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.") String name, @Past Date joinDate,
                String password, String ssn, String address) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
        this.address = address;
    }
}
