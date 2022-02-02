package com.sparta.springboardwithcomment.web.login;


import com.sparta.springboardwithcomment.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginDto form){
        return "/login/form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("form") LoginDto form, BindingResult bindingResult, HttpServletRequest request){
        //로그인 실패
        Member result = loginService.login(form);
        if(result==null){
            bindingResult.reject(null, null, "아이디와 패스워드를 확인해주세요.");
        }

        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "/login/form";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("loginSession", result);

        return "redirect:/";

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession(false);
        if(session !=null){
            session.invalidate();
        }

        expireCookie(response,"JSESSIONID");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
