package com.springBoot.blogApp.controllers;

import com.springBoot.blogApp.payloads.ApiResponse;
import com.springBoot.blogApp.payloads.UserDto;
import com.springBoot.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST-create user
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){  //ResponseEntity use for configure http response
       UserDto addedUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(addedUserDto, HttpStatus.CREATED);
    }

    //Get - get All user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUser = this.userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }


    //Get - get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    //PUT- update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
        System.out.println("Update User details: "+ userDto);
        UserDto updatedUser = this.userService.updateUserById(userDto, userId);
      //  return  new ResponseEntity<>(updatedUser,HttpStatus.OK);
        return ResponseEntity.ok(updatedUser);

    }

    //Delete- delete User

//    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
//        this.userService.deleteUserById(userId);
//        return new ResponseEntity<>(Map.of("Message","User Deleted Successfully"), HttpStatus.OK);
//    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
    }



}
