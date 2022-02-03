package com.sparta.springboardwithcomment.web.board;

import com.sparta.springboardwithcomment.domain.board.Board;
import com.sparta.springboardwithcomment.domain.board.BoardRepository;
import com.sparta.springboardwithcomment.domain.board.BoardService;
import com.sparta.springboardwithcomment.domain.comment.Comment;
import com.sparta.springboardwithcomment.domain.comment.CommentRepository;
import com.sparta.springboardwithcomment.domain.comment.CommentService;
import com.sparta.springboardwithcomment.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/boards")
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping
    public String showBoards(Model model, HttpServletRequest request) {
       boardService.showAllBoards(model,request);
        return "boards/boards";
    }

    @GetMapping("/add")
    public String boardForm(@ModelAttribute("board") BoardDto boardDto) {
        return "boards/form";
    }

    @PostMapping("/add")
    public String saveBoards(@Validated @ModelAttribute("board") BoardDto board, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "boards/form";
        }
        boardService.saveBoard(board, request);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model, HttpServletRequest request) {
        boardService.showBoard(id, model, request);
        commentService.findAllComments(id, model);
        return "boards/board";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, @ModelAttribute Board board) {

        boardService.deleteBoard(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        boardService.editForm(id, model);
        return "boards/editForm";
    }

    @PostMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, @Validated @ModelAttribute("board") BoardDto board, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "boards/editForm";
        }

        boardService.editBoard(id, board);
        return "redirect:/boards/{id}";
    }



}
