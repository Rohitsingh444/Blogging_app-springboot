package com.springBoot.blogApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean    //Spring continer automatic create the object, and we can use anywhere with autowired annotation
	public ModelMapper modelMapper(){         // we can create separate configure class and define their
		return new ModelMapper();             // ModelMapper use for copy the data of one object to another object
	}

}
