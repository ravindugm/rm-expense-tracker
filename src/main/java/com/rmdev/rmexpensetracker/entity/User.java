package com.rmdev.rmexpensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
