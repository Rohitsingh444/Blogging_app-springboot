package com.springBoot.blogApp.services.Implements;

import com.springBoot.blogApp.entities.User;
import com.springBoot.blogApp.exceptions.ResourceNotFoundException;
import com.springBoot.blogApp.payloads.UserDto;
import com.springBoot.blogApp.repositories.UserRepository;
import com.springBoot.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser =this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = this.userRepository.findAll();
//        List<UserDto> allUserDto = new ArrayList<>();
//        for (User u: users) {
//            UserDto userDto = this.userToDto(u);
//            allUserDto.add(userDto);
//        }
        List<UserDto> allUserDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        System.out.println("All user Data:::"+ allUserDto);
        return allUserDto;
    }
    @Override
    public UserDto getUserById(int userId){
       // User user = this.userRepository.findById(userId).get();
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User"," Id",userId));

        return this.userToDto(user);
    }
    @Override
    public UserDto updateUserById(UserDto userDto, int userId){
       // User user = this.userRepository.findById(userId).get();
        // or
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User"," Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        System.out.println("Data after Update ::"+user);
        User upDatedUser =this.userRepository.save(user);
        return this.userToDto(upDatedUser);
    }
    @Override
    public Void deleteUserById(int userId){
        //this.userRepository.delete(this.userRepository.findById(userId).get());
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User"," Id",userId));
        this.userRepository.delete(user);

        return null;
    }

    //userDto = which we are passing through payload
    //user = user data which we are storing in DB (both are same ype of data)
    // this method convert userDto data to user
    public User dtoToUser(UserDto userDto){   // user for post and pull
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
