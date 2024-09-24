package com.HelpdeskDemo.app.dto;


import lombok.Data;

@Data
public class SignUpDto {

    private Long id;
    private String name;
    private String email;
    private String password;

}
