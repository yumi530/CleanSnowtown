package com.project.smartclean.board.service;

import com.project.smartclean.board.dto.CommentDto;
import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Comment;
import com.project.smartclean.board.repository.BoardRepository;
import com.project.smartclean.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public Long save(CommentDto commentDto) {
        Optional<Board> optionalBoard = boardRepository.findById(commentDto.getBoardNo());
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            Comment comment = Comment.toEntity(commentDto, board);
            return commentRepository.save(comment).getCommentId();
        } else {
            return null;
        }
    }

    @Override
    public List<CommentDto> findAll(Long boardNo) {
        Board board = boardRepository.findById(boardNo).get();
        List<Comment> commentList = commentRepository.findAllByBoardOrderByCommentIdDesc(board);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = CommentDto.toCommentDto(comment, boardNo);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
