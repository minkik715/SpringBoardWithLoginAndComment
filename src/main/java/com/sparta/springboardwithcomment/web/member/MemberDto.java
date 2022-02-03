package com.sparta.springboardwithcomment.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MemberDto {

    @NotEmpty
    @Size(min=2, max=20)
    private String name;

    @NotEmpty
    @Pattern(regexp="[a-zA-Z0-9]{3,20}", message = "아이디는 영어와 숫자로 포함해서 3~20자리 이내로 입력해주세요.")
    private String userId;

    @NotEmpty
    @Pattern(regexp="[a-zA-Z0-9]{3,20}", message = "비밀번호는 영어와 숫자로 포함해서 3~20자리 이내로 입력해주세요.")
    private String password;

    @NotEmpty
    @Pattern(regexp="[a-zA-Z0-9]{3,20}", message = "비밀번호는 영어와 숫자로 포함해서 3~20자리 이내로 입력해주세요.")
    private String passwordCheck;


    public MemberDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
