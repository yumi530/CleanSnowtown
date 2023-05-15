package com.project.smartclean.faq.service;

import com.project.smartclean.board.entity.Search;
import com.project.smartclean.faq.dto.FaqDto;
import com.project.smartclean.faq.entity.Faq;
import com.project.smartclean.notice.dto.NoticeDto;
import com.project.smartclean.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqService {
    Page<Faq> getFaqList(Search search, Pageable pageable);

    void insertFaq(FaqDto faqDto);

    FaqDto readFaq(Long faqNo);

    FaqDto updateFaq(FaqDto faqDto);

    void deleteFaq(FaqDto faqDto);
}
