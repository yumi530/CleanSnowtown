package com.project.smartclean.notice.entity;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.notice.dto.NoticeDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(exclude = "member")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
    @Id
    @GeneratedValue
    private Long noticeNo;
    private String noticeWriteName;
    private String noticeTitle;
    private String noticeContents;
    private int noticeCnt;
    private LocalDateTime noticeWriteDate;
    private LocalDateTime noticeUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getNotices().add(this);
    }


    public static Notice toEntity(NoticeDto noticeDto) {
        return Notice.builder()
                .noticeNo(noticeDto.getNoticeNo())
                .noticeTitle(noticeDto.getNoticeTitle())
                .noticeContents(noticeDto.getNoticeContents())
                .noticeCnt(noticeDto.getNoticeCnt())
                .noticeWriteDate(LocalDateTime.now())
                .noticeUpdateDate(LocalDateTime.now())
                .noticeWriteName(noticeDto.getUserId())
                .build();
    }

}
