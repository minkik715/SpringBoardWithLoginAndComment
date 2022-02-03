package com.sparta.springboardwithcomment.domain.member;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Member {

    public Member(String name, String userId, String password ) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.KakaoId = null;
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

    @Column(nullable = true)
    private Long KakaoId;


    public Member(String name, String userId, String password, Long KakaoId) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.KakaoId = KakaoId;
    }
}
