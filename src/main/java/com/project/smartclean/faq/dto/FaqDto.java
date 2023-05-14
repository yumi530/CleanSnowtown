package com.project.smartclean.faq.dto;

import com.project.smartclean.faq.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaqDto {
    private Long faqNo;
    private String faqTitle;
    private String faqContents;
    private int faqCnt;
    private LocalDateTime faqWriteDate;
    private LocalDateTime faqUpdateDate;
    private String userId;
    private String faqWriteName;

    public static FaqDto of(Faq faq) {

        return FaqDto.builder()
                .faqNo(faq.getFaqNo())
                .faqTitle(faq.getFaqTitle())
                .faqContents(faq.getFaqContents())
                .faqCnt(faq.getFaqCnt())
                .faqWriteDate(LocalDateTime.now())
                .faqUpdateDate(LocalDateTime.now())
                .faqWriteName(faq.getFaqWriteName())
                .build();

    }


//    public void setUserId(String username) {
//        this.userId = username;
//
//    }
}
