package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardService {

//    void insertBoard(Board board);

//    void insertBoard(Board board) throws IOException;

    void insertBoard(Board board, MultipartFile file) throws IOException;

    Board readBoard(Long boardNo);
    void updateBoard(Board board);
    void deleteBoard(Board board);
    void updateView(Long boardNo);

    Page<Board> getBoardList(Search search, Pageable pageable);
}
