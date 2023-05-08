package com.project.smartclean.board.dto;

import com.project.smartclean.board.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private  Long commentId;
    private Long boardNo;
    private String commentWriter;
    private String comments;
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(Comment comment, Long boardNo) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setCommentWriter(comment.getCommentWriter());
        commentDto.setComments(comment.getComments());
        commentDto.setCommentCreatedTime(comment.getCreatedTime());
        commentDto.setBoardNo(boardNo);
        return commentDto;
    }
}
