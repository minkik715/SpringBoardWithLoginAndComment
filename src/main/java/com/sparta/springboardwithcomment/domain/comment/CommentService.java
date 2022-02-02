package com.sparta.springboardwithcomment.domain.comment;

import com.sparta.springboardwithcomment.domain.board.Board;
import com.sparta.springboardwithcomment.domain.board.BoardRepository;
import com.sparta.springboardwithcomment.domain.member.Member;
import com.sparta.springboardwithcomment.web.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    /*
    댓글 관련 기능
    1. 댓글 추가하는 기능
    2. 댓글 수정하는 기능
    3. 댓글 삭제하는 기능
    4. 댓글 조회하는 기능
    */

    public void findAllComments(Long id, Model model){
        findCommentInMethod(id, model);
    }

    public void saveComment(CommentDto comment, Long id, Member writer) {
        Comment saveComment = new Comment(comment.getComment(),writer.getName(),id);
        log.info("commentInfo={}", comment);
        commentRepository.save(saveComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public void editForm(Long id, Long boardId, Model model) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        model.addAttribute("board", findBoard);
        findCommentInMethod(boardId, model);
        model.addAttribute("editId", id);
    }

    private void findCommentInMethod(Long boardId, Model model) {
        List<Comment> commentList = commentRepository.findAll();
        List<Comment> sendComment = new LinkedList<>();
        for (Comment comment : commentList) {
            if(comment.getBoardId() == boardId){
                sendComment.add(comment);
            }
        }
        model.addAttribute("comments",sendComment);
    }

    public void editComment(Long id, CommentDto comment) {
        Comment findComment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 댓글은 없어!")
        );
        findComment.setComment(comment.getComment());
        commentRepository.save(findComment);
    }
}
