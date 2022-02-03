package com.sparta.springboardwithcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableJpaAuditing
@SpringBootApplication
public class SpringBoardWithCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardWithCommentApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }


}
