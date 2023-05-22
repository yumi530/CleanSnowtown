package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardService {

//    void insertBoard(Board board, User user, MultipartFile file) throws IOException;

    Board insertBoard(Board board, Member member, MultipartFile file) throws IOException;



    Board readBoard(Long boardNo);

//    Board readBoard(Board board);

    Board updateBoard(Board board, Member member, MultipartFile file) throws IOException;

    void deleteBoard(Board board);

    Page<Board> getBoardList(Search search, Pageable pageable);
    Page<Board> getBoardListWithMember(Search search, Pageable pageable);
}