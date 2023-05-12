package com.project.smartclean.faq.service;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.faq.dto.FaqDto;
import com.project.smartclean.faq.entity.Faq;
import com.project.smartclean.faq.entity.QFaq;
import com.project.smartclean.faq.repository.FaqRepository;
import com.project.smartclean.notice.dto.NoticeDto;
import com.project.smartclean.notice.entity.Notice;
import com.project.smartclean.notice.entity.QNotice;
import com.project.smartclean.notice.repository.NoticeRepository;
import com.project.smartclean.notice.service.NoticeService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {
    private final FaqRepository faqRepository;

    @Override
    public Page<Faq> getFaqList(Search search, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QFaq qFaq = QFaq.faq;
        if (search.getSearchCondition().equals("faqTitle")) {
            builder.and(qFaq.faqTitle.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("faqContents")) {
            builder.and(qFaq.faqContents.like("%" + search.getSearchKeyword() + "%"));
        }
        return faqRepository.findAll(builder, pageable);
    }

    @Override
    public void insertFaq(FaqDto faqDto) {

        Faq faq = Faq.builder()
                .faqNo(faqDto.getFaqNo())
                .faqWriteName(faqDto.getFaqWriteName())
                .faqTitle(faqDto.getFaqTitle())
                .faqContents(faqDto.getFaqContents())
                .faqWriteDate(LocalDateTime.now())
                .build();
        faqRepository.save(faq);
    }

    @Override
    public FaqDto readFaq(Long faqNo) {
        Faq faq = faqRepository.findById(faqNo).get();
        faq.setFaqCnt(faq.getFaqCnt());
        faqRepository.save(faq);
        return FaqDto.of(faq);
    }
    @Override
    public void updateView(Long faqNo) {
        faqRepository.updateViews(faqNo);
    }

    @Override
    public FaqDto updateFaq(FaqDto faqDto) {
        Optional<Faq> optionalFaq = faqRepository.findById(faqDto.getFaqNo());
        if (!optionalFaq.isPresent()) {
            return null;
        }
        Faq faq = optionalFaq.get();
        faq.setFaqNo(faqDto.getFaqNo());
        faq.setFaqWriteName(faqDto.getUserId());
        faq.setFaqTitle(faqDto.getFaqTitle());
        faq.setFaqContents(faqDto.getFaqContents());
        Faq result = faqRepository.save(faq);
        return FaqDto.of(result);
    }

    @Override
    public void deleteFaq(FaqDto faqDto) {
        faqRepository.deleteById(faqDto.getFaqNo());
    }

}
