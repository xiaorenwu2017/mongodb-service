package com.qjc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;

    private String userName;

    private String address;

    private Integer age;

    private String birthday;

}
