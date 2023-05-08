package com.project.smartclean.board.entity;

import com.project.smartclean.board.dto.CommentDto;
import com.project.smartclean.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comments;

    private String commentWriter;
//    private LocalDateTime commentCreatedAt;
//    //private LocalDateTime rpyModifiedAt;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    public static Comment toEntity(CommentDto commentDto, Board board) {
        Comment comment = new Comment();
        comment.setCommentWriter(commentDto.getCommentWriter());
        comment.setComments(commentDto.getComments());
        //comment.setCommentCreatedAt(commentDto.getCommentCreatedAt());
        comment.setBoard(board);
        return comment;
    }
}
