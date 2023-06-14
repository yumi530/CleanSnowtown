package com.project.smartclean.board.controller;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.board.dto.BoardDto;
import com.project.smartclean.board.dto.CommentDto;
import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.board.service.BoardService;
import com.project.smartclean.board.service.CommentService;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    private final CommentService commentService;

    @RequestMapping("/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("title");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");
//        Page<Board> list = boardService.getBoardList(search, pageable);
        Page<Board> list = boardService.getBoardListWithMember(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
//        model.addAttribute("listBoard", listBoard);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/list";
    }

    @GetMapping("/insert")
    public String insertBoard(Model model, @AuthenticationPrincipal Member member) {
        MemberDto memberDto = memberService.detail(member.getUsername());
        model.addAttribute("writeName", memberDto.getName());
        return "board/insert";
    }

    @PostMapping("/insert")
    public String insertBoardSubmit(Board board, @AuthenticationPrincipal Member member, MultipartFile file) throws IOException {
        boardService.insertBoard(board, member, file);
        return "redirect:list";
    }

    @GetMapping("/read")
    public String readBoard(Model model, Long boardNo) {
        Board board = boardService.readBoard(boardNo);
        List<CommentDto> commentDtoList = commentService.findAll(boardNo);
        model.addAttribute("commentList", commentDtoList);
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

    @PreAuthorize("@webSecurity.checkAuthority(authentication, #boardNo)")
    @GetMapping("/update")
    public String updateForm(Long boardNo, Model model) {
        Board readBoard = boardService.readBoard(boardNo);
        model.addAttribute("boardUpdate", readBoard);
        return "board/modify";
    }

    @PostMapping("/update")
    public String updateBoard(Board board, @AuthenticationPrincipal Member member, MultipartFile file) throws IOException {
        boardService.updateBoard(board, member, file);
        return "redirect:list";
    }

    @PreAuthorize("@webSecurity.checkAuthority(authentication, #boardNo)")
    @PostMapping("/delete")
    public String deleteBoard(Long boardNo) {
        boardService.deleteBoard(boardNo);
        return "redirect:list";
    }
}