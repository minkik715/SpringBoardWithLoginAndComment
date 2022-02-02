package com.sparta.springboardwithcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SpringBoardWithCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardWithCommentApplication.class, args);
    }

}
