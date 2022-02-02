package com.sparta.springboardwithcomment.web.comment;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CommentDto {

    @Nullable
    private String comment;

    private Long boardId;

    private String writerName;

}
