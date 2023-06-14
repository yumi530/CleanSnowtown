package com.project.smartclean.board.controller;

import com.project.smartclean.board.dto.CommentDto;
import com.project.smartclean.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDto commentDto) {
        Long saveResult = commentService.save(commentDto);
        if (saveResult != null) {
           List<CommentDto> commentDtoList = commentService.findAll(commentDto.getBoardNo());
           return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다." , HttpStatus.NOT_FOUND);
        }

    }
}
