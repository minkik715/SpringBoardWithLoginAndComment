package com.sparta.springboardwithcomment.domain.board;

import com.sparta.springboardwithcomment.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
}
