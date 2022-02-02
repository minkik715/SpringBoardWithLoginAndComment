package com.sparta.springboardwithcomment.web.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardDto {

    @NotEmpty
    private String name;

    private Long writerId;

    @NotEmpty
    private String contents;

}
