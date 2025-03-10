package com.group.libraryapp.domain.user;


import com.group.libraryapp.domain.user.userloanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 스프링이 User 클래스와 user 테이블이 서로 바라보도록 해주는 어노테이션
// 엔티티 : 저장되고 관리되어야 하는 데이터를 의미한다.

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id를 고유값으로 간주하고, 자동생성한다.
    private final Long id = null;

    //   @Column(nullable = false, length = 20, )


    @Column(nullable = false, length = 20, name="name")
    private String name;
    // 매핑 어노테이션
    // 객체의 필드명과 테이블의 필드명이 같은 경우 name 파트는 생략이 가능하다.
    // 또한 이름이 같고, 별 다른 변경사항이 없는 경우 모두 생략이 가능하다.


    // @Column(nullable = true,name="age")
    private Integer age;
    // null 이어도 괜찮고, 길이의 제한도 없고, 데이터 타입도 같기 때문에 컬럼 어노테이션을 생략한다.

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER) // 연관 관계의 주인
    // cascade 옵션을 사용하면 관계를 맺은 ( id를 가지고 있는 ) 객체들도 같이 삭제 된다.
    // orphanRemoval 옵션을 사용하면 테이블간의 관계를 끊으면 해당 객체가 삭제된다.
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User(){};

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name 값이 들어왔습니다.",name));
        } // 예외처리
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }
    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst().orElseThrow(IllegalArgumentException::new);

        targetHistory.doReturn();
    }

}
