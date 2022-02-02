package com.sparta.springboardwithcomment.web.login;

import com.sparta.springboardwithcomment.domain.member.Member;
import com.sparta.springboardwithcomment.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(LoginDto loginDto){
        log.info("loginInfo={}",loginDto);
        return memberRepository.findAll().stream().filter(member->member.getPassword().equals(loginDto.getPassword()))
                .filter(member -> member.getUserId().equals(loginDto.getUserId()))
                .findAny()
                .orElse(null);
    }
}
