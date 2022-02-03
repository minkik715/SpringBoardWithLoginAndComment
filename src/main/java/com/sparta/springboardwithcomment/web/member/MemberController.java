package com.sparta.springboardwithcomment.web.member;

import com.sparta.springboardwithcomment.domain.member.Member;
import com.sparta.springboardwithcomment.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String memberAddForm(@ModelAttribute("member") MemberDto member){
        return "/members/form";
    }

    @PostMapping("/add")
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
        String encodingPassword = passwordEncoder.encode(member.getPassword());
        //회원 가입 성공
        Member saveMember = new Member(member.getName(),member.getUserId(), encodingPassword);
        log.info("saveMember={}", saveMember);
        memberRepository.save(saveMember);

        return "redirect:/login";

    }

}
