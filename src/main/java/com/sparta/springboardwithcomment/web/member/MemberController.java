package com.sparta.springboardwithcomment.web.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springboardwithcomment.domain.member.Member;
import com.sparta.springboardwithcomment.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {


    private final MemberRepository memberRepository;
    private final MemberService memberService;
    @GetMapping("/members/add")
    public String memberAddForm(@ModelAttribute("member") MemberDto member){
        return "/members/form";
    }

    @PostMapping("/members/add")
    public String memberSave(@Validated @ModelAttribute("member") MemberDto member, BindingResult bindingResult){

        if(!member.getPassword().equals(member.getPasswordCheck())){
            bindingResult.reject(null, null, "비밀번호가 서로 다릅니다, 확인해주세요.");
        }

        if(member.getPassword().equals(member.getUserId())){
            bindingResult.reject(null, null, "비밀번호와 아이디는 같을 수 없습니다.");
        }

        Member member2 = memberRepository.findAll().stream().filter(member1 -> member1.getUserId().equals(member.getUserId())).findAny().orElse(null);
        if(member2!=null){
            bindingResult.reject(null, null, "이미 존재하는 아이디 입니다.");
        }
        Member member3 = memberRepository.findAll().stream().filter(member1 -> member1.getName().equals(member.getName())).findAny().orElse(null);
        if(member3!=null){
            bindingResult.reject(null, null, "이미 존재하는 닉네임 입니다.");
        }

        //회원 가입 실패
        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "/members/form";
        }

        memberService.signUp(member);
        return "redirect:/login";

    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletRequest request) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드
        log.info("Hello here!");
        memberService.kakaoLogin(code, request);
        return "redirect:/";
    }

}
