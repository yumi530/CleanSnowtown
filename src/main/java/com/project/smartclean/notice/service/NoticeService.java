package com.project.smartclean.notice.service;

import com.project.smartclean.board.entity.Search;
import com.project.smartclean.notice.dto.NoticeDto;
import com.project.smartclean.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    Page<Notice> getNoticeList(Search search, Pageable pageable);

    void insertNotice(NoticeDto noticeDto);

    NoticeDto readNotice(Long noticeNo);

//    Notice readNotice(Long noticeDto);

//    void updateView(Long noticeNo);

    NoticeDto updateNotice(NoticeDto noticeDto);


    void deleteNotice(NoticeDto noticeDto);
}
