package com.sparta.springboardwithcomment.domain.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String writerId;

    @Column(nullable = false)
    private String contents;

    public Board(String name, String contents, String writerId) {
        this.name = name;
        this.contents = contents;
        this.writerId = writerId;
    }

    public Board() {

    }
}
