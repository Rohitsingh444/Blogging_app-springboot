package com.springBoot.blogApp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {    // custom exception

    String resourceName;
    String fieldName;
    long filedValue;

    //constructor using fields
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
      super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue)); //super(message)
      this.resourceName = resourceName;
      this.fieldName = fieldName;
      this.filedValue = fieldValue;
    }
}
