package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id = null;

    @Column(nullable = false)
    // @Column(nullable = false, length = 255, name = "name")
    // 테이블 필드명과 같다면 name 생략가능, 최대 길이도 같다면 생략 가능
    private String name;

    protected Book(){};
    // 엔티티 클래스는 기본 생성자가 무조건 하나 필요하다.


    public Book(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
