package com.project.smartclean.faq.repository;

import com.project.smartclean.faq.entity.Faq;
import com.project.smartclean.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> , QuerydslPredicateExecutor<Faq> {

    @Modifying
    @Query("update Faq f set f.faqCnt = f.faqCnt + 1 where f.faqNo = :faqNo")
    int updateViews(Long faqNo);
}
