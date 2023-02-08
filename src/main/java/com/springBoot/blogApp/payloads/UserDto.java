package com.springBoot.blogApp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString  // user to print UserDto Object in console in string format
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    // spring validation step:
    // 1. add spring start validation dependency in pom.xml
    // 2. add all validation annotation in entity class
    // 2. use @Valid annotation in controller class in required APIs

    private int id;
    @NotEmpty(message = "Name can't Null")  //not-blank+NotNull= not Empty
    @Size(min = 4, message = "name should have least 4 characters")
    private String name;
    @Email(message ="Invalid email address '${validatedValue}'")
    @NotEmpty(message = "Email can not be empty")
    private String email;
    @NotEmpty(message = "Password '${validatedValue}' must not be empty")  //'${validatedValue}' is for print value of key
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "'${validatedValue}' is not correct")
    @Size(min=8,max = 20, message = "password must be min of 8 character and max of 20 characters")
    private String password;
    @NotEmpty(message = "about cant be null")
    private String about;

}
