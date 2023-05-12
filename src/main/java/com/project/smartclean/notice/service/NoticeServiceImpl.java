package com.project.smartclean.notice.service;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.notice.dto.NoticeDto;
import com.project.smartclean.notice.entity.Notice;
import com.project.smartclean.notice.entity.QNotice;
import com.project.smartclean.notice.repository.NoticeRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Page<Notice> getNoticeList(Search search, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QNotice qNotice = QNotice.notice;
        if (search.getSearchCondition().equals("noticeTitle")) {
            builder.and(qNotice.noticeTitle.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("noticeContents")) {
            builder.and(qNotice.noticeContents.like("%" + search.getSearchKeyword() + "%"));
        }
        return noticeRepository.findAll(builder, pageable);
    }

    @Override
    public void insertNotice(NoticeDto noticeDto) {
        Notice notice = Notice.builder()
                .noticeNo(noticeDto.getNoticeNo())
                .noticeWriteName(noticeDto.getNoticeWriteName())
                .noticeTitle(noticeDto.getNoticeTitle())
                .noticeContents(noticeDto.getNoticeContents())
//                .noticeCnt(noticeDto.getNoticeCnt())
                .noticeWriteDate(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);
    }

    @Override
    public NoticeDto readNotice(Long noticeNo) {
        Notice notice = noticeRepository.findById(noticeNo).get();
        notice.setNoticeCnt(notice.getNoticeCnt());
        noticeRepository.save(notice);
        return NoticeDto.of(notice);
    }
    @Override
    public void updateView(Long boardNo) {
        noticeRepository.updateViews(boardNo);
    }

//    @Override
//    public Notice readNotice(Long noticeNo) {
//        Notice notice = noticeRepository.findById(noticeNo).get();
//        notice.setNoticeCnt(notice.getNoticeCnt());
//        Notice result = noticeRepository.save(notice);
//        return result;
//    }

    @Override
    public NoticeDto updateNotice(NoticeDto noticeDto) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeDto.getNoticeNo());
        if (!optionalNotice.isPresent()) {
            return null;
        }
        Notice notice = optionalNotice.get();
        notice.setNoticeNo(noticeDto.getNoticeNo());
        notice.setNoticeWriteName(noticeDto.getUserId());
        notice.setNoticeTitle(noticeDto.getNoticeTitle());
        notice.setNoticeContents(noticeDto.getNoticeContents());
//        notice.setNoticeCnt(noticeDto.getNoticeCnt());
        Notice result = noticeRepository.save(notice);
        return NoticeDto.of(result);
    }

    @Override
    public void deleteNotice(NoticeDto noticeDto) {
        noticeRepository.deleteById(noticeDto.getNoticeNo());
    }

}
