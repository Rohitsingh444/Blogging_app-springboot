package com.springBoot.blogApp;

import com.springBoot.blogApp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {
	@Autowired
	private UserRepository userRepository;  //userrepository is interface we cant autowired
	                                        // springboot dynamic create the implement class of this interface through
	                                        // which we autowired the interface

	@Test
	void contextLoads() {
	}

	public void repoTest(){
		String className = this.userRepository.getClass().getName();
		String packageName = this.userRepository.getClass().getPackageName();
		System.out.println("Class name of Which implemented the userRepo Interface"+ className);
		System.out.println("Package Name:"+ packageName);
	}

}
