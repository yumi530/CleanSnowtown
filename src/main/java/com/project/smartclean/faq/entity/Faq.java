package com.project.smartclean.faq.entity;

import com.project.smartclean.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(exclude = "member")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Faq {
    @Id
    @GeneratedValue
    private Long faqNo;
    private String faqWriteName;
    private String faqTitle;
    private String faqContents;
    private int faqCnt;
    private LocalDateTime faqWriteDate;
    private LocalDateTime faqUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

//    public void setMember(Member member) {
//        this.member = member;
//        member.getNotices().add(this);
//    }


//    public static Faq toEntity(NoticeDto noticeDto) {
//        return Faq.builder()
//                .noticeNo(noticeDto.getNoticeNo())
//                .noticeTitle(noticeDto.getNoticeTitle())
//                .noticeContents(noticeDto.getNoticeContents())
//                .noticeCnt(noticeDto.getNoticeCnt())
//                .noticeWriteDate(LocalDateTime.now())
//                .noticeUpdateDate(LocalDateTime.now())
//                .noticeWriteName(noticeDto.getUserId())
//                .build();
//    }

}
