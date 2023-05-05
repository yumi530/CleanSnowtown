package com.project.smartclean.board.controller;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.board.service.BoardService;
import com.project.smartclean.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault(page =  0 , size = 10, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("title");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");
        Page<Board>list = boardService.getBoardList(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 , 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/list";
    }
    @GetMapping("/insert")
    public String insertBoard(){
        return "board/insert";
    }
    @PostMapping("/insert")
    public String insertBoardSubmit(Board board, @AuthenticationPrincipal User user, MultipartFile file){
        //MemberDto memberDto = memberService.detail(user.getUsername());
        board.setWriteName(user.getUsername());
        try {
            boardService.insertBoard(board, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:list";
    }

    @GetMapping("/read")
    public String readBoard(Model model, Long boardNo) {

        Board board = boardService.readBoard(boardNo);
        boardService.updateView(boardNo);

        model.addAttribute("board", board);

        /*
        if (result != null) {
            model.addAttribute("result", result);
        } else {
            model.addAttribute("null", new Board());
        }
         */

        return "board/read";
    }
    @PostMapping("/update")
    public String updateBoard(Board board){
        boardService.updateBoard(board);
        return "forward:board/list";
    }
    @GetMapping("/delete")
    public String deleteBoard(Board board){
        boardService.deleteBoard(board);
        return "forward:board/list";
    }

}
