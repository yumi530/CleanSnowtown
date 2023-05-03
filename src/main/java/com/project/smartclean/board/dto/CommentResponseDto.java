//package com.project.smartclean.board.dto;
//
//import com.project.smartclean.board.entity.Board;
//import com.project.smartclean.board.entity.Comment;
//import com.project.smartclean.member.entity.Member;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class CommentResponseDto {
//private Long commentId;
//private String comment;
//private String rpyCreatedDt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//private String rpyModifiedDt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//private Member member;
//private Board board;
//
///* Dto -> Entity */
//public Comment toEntity() {
//Comment comments = Comment.builder()
//.commentId(commentId)
//.comment(comment)
//.rpyCreatedAt(rpyCreatedDt)
//.rpyModifiedAt(rpyModifiedDt)
////.member(member)
////.board(board)
//.build();
//
//return comments;
//}
//
//}
