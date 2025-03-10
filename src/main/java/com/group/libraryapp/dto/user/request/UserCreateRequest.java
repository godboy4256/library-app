package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age;

    // int : null 이 안됨
    // Integer : null 가능 -> 나이는 필수가 아님.

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
