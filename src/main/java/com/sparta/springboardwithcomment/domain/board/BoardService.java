package com.sparta.springboardwithcomment.domain.board;

import com.sparta.springboardwithcomment.domain.member.Member;
import com.sparta.springboardwithcomment.web.board.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    /*
    리팩토링 기능
    1. 글 전체를 보여주는 기능
    2. 특정 글을 조회하는 기능
    3. 글을 작성하는 기능
    4. 글을 수정하는 기능
    5. 글을 삭제하는 기능
     */

    //글 전체를 보여주는 함수

    public void showAllBoards(Model model, HttpServletRequest request){
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member loginSession = (Member) session.getAttribute("loginSession");
            if (loginSession == null) {
                model.addAttribute("username", "비로그인 유저");
            } else {
                String name = loginSession.getName();
                model.addAttribute("username", name);
            }
        } else {
            model.addAttribute("username", "비로그인 유저");
        }
        model.addAttribute("boards", boards);
    }


    public void saveBoard(BoardDto board, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("loginSession");
        String userId = member.getUserId();
        Board saveBoard = new Board(board.getName(), board.getContents(), userId);
        boardRepository.save(saveBoard);
        log.info("saveBaord={}", saveBoard);
    }

    public void showBoard(Long id, Model model, HttpServletRequest request) {
        Board findBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginSession");
        String userid;
        String userName;
        if (member == null) {
            userid = " ";
            userName= " ";
        } else {
            userid = member.getUserId();
            userName = member.getName();
        }
        log.info("username ?= boardWrite= {}, {}", userid, findBoard.getWriterId());
        model.addAttribute("userId", userid);
        model.addAttribute("userName", userName);
        model.addAttribute("board", findBoard);
        log.info("findBoard={}", findBoard);
    }

    public void editForm(Long id, Model model) {
        Board findBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 글은 존재하지 않아!")
        );
        model.addAttribute("board", findBoard);
    }

    public void editBoard(Long id, BoardDto board) {
        Board findBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 글은 존재하지 않아!")
        );
        findBoard.setName(board.getName());
        findBoard.setContents(board.getContents());
        boardRepository.save(findBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
