package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<Board> boardList (Board board);
//    void insertBoard(Board board);

//    void insertBoard(Board board) throws IOException;

    void insertBoard(Board board, MultipartFile file) throws IOException;

    Board readBoard(Long boardNo);
    void updateBoard(Board board);
    void deleteBoard(Board board);
    void updateView(Long boardNo);
}
