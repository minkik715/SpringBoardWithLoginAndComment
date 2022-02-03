package com.sparta.springboardwithcomment.web.comment;


import com.sparta.springboardwithcomment.domain.board.Board;
import com.sparta.springboardwithcomment.domain.board.BoardRepository;
import com.sparta.springboardwithcomment.domain.comment.Comment;
import com.sparta.springboardwithcomment.domain.comment.CommentRepository;
import com.sparta.springboardwithcomment.domain.comment.CommentService;
import com.sparta.springboardwithcomment.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/boards")
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{id}/comment")
    public String comment(@PathVariable Long id, @ModelAttribute("comment") CommentDto comment, BindingResult bindingResult, @SessionAttribute("loginSession") Member writer){
        commentService.saveComment(comment, id, writer);
        return "redirect:/boards/{id}";
    }

    @GetMapping("{boardId}/comment/delete/{id}")
    public String commentDelete(@PathVariable Long boardId, @PathVariable Long id){
        commentService.deleteComment(id);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("{boardId}/comment/edit/{id}")
    public String commentEditForm(@PathVariable Long boardId, @PathVariable Long id, Model model){
        commentService.editForm(id, boardId, model);
        return"boards/editComment";
    }


    @PostMapping("{boardId}/comment/edit/{id}")
    public String commentEdit(@PathVariable Long boardId, @PathVariable Long id, @ModelAttribute CommentDto comment ){
        commentService.editComment(id,comment);
        return "redirect:/boards/{boardId}";
    }

}
