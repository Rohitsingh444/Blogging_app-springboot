package com.springBoot.blogApp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString  // user to print UserDto Object in console in string format
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;

}
