package com.sparta.springboardwithcomment.domain.comment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String writerName;

    public Comment(String comment, String writerName, Long boardId) {
        this.comment = comment;
        this.writerName = writerName;
        this.boardId = boardId;
    }

    @Column(nullable = false)
    private Long boardId;


    public Comment() {

    }
}
