package com.sparta.springboardwithcomment.domain.member;


import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Entity
public class Member {

    public Member(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;


    public Member() {

    }
}
