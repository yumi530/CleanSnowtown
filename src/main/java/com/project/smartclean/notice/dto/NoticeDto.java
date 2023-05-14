package com.project.smartclean.notice.dto;

import com.project.smartclean.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto {
    private Long noticeNo;
    private String noticeTitle;
    private String noticeContents;
    private int noticeCnt;
    private LocalDateTime noticeWriteDate;
    private LocalDateTime noticeUpdateDate;
    private String userId;
    private String noticeWriteName;

    public static NoticeDto of(Notice notice) {

        return NoticeDto.builder()
                .noticeNo(notice.getNoticeNo())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContents(notice.getNoticeContents())
                .noticeCnt(notice.getNoticeCnt())
                .noticeWriteDate(LocalDateTime.now())
                .noticeUpdateDate(LocalDateTime.now())
                .noticeWriteName(notice.getNoticeWriteName())
                .build();

    }


//    public void setUserId(String username) {
//        this.userId = username;
//
//    }
}
