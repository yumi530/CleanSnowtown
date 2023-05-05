package com.project.smartclean.board.repository;

import com.project.smartclean.board.entity.Board;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
    @Modifying
    @Query("update Board b set b.cnt = b.cnt + 1 where b.boardNo = :boardNo")
    int updateViews(Long boardNo);

}
