package com.project.smartclean.board.repository;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardOrderByCommentIdDesc(Board board);
}
