package com.project.smartclean.board.service;

import com.project.smartclean.board.dto.CommentDto;

import java.util.List;

public interface CommentService {
    Long save(CommentDto commentDto);

    List<CommentDto> findAll(Long boardNo);
}
