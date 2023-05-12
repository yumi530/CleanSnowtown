package com.project.smartclean.notice.repository;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.notice.entity.Notice;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> , QuerydslPredicateExecutor<Notice> {

    @Modifying
    @Query("update Notice n set n.noticeCnt = n.noticeCnt + 1 where n.noticeNo = :noticeNo")
    int updateViews(Long noticeNo);
}
