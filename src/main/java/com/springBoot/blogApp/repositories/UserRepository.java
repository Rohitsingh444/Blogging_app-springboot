package com.springBoot.blogApp.repositories;

import com.springBoot.blogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
