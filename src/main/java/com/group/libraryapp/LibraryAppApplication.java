package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 어노테이션 : @ 로 시작
// Spring 설정이 자동으로 이루어지는 역할

public class LibraryAppApplication {
  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args);
    // Spring 어플리케이션 시작
  }
}
