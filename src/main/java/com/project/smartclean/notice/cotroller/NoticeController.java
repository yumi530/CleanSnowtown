package com.project.smartclean.notice.cotroller;


import com.project.smartclean.board.entity.Search;
import com.project.smartclean.notice.dto.NoticeDto;
import com.project.smartclean.notice.entity.Notice;
import com.project.smartclean.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    @GetMapping("/list")
    public String noticeList(Model model, @PageableDefault(page = 0, size = 10, sort = "noticeNo", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("noticeTitle");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        Page<Notice> list = noticeService.getNoticeList(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "notice/list";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/insert")
    public String insertNotice() {
        return "notice/insert";
    }

    @PostMapping("/insert")
    public String insertNoticeSubmit(NoticeDto noticeDto, @AuthenticationPrincipal User user) {
        noticeDto.setNoticeWriteName(user.getUsername());
        noticeService.insertNotice(noticeDto);

        return "redirect:list";
    }

    @GetMapping("/read")
    public String readNotice(Model model, Long noticeNo) {
        NoticeDto noticeDto = noticeService.readNotice(noticeNo);
        model.addAttribute("notice", noticeDto);
        return "notice/read";
    }

    @GetMapping("/update")
    public String updateNotice(Long noticeNo, Model model) {
        NoticeDto noticeDto = noticeService.readNotice(noticeNo);
        model.addAttribute("noticeUpdate", noticeDto);
        return "notice/modify";
    }

    @PostMapping("/update")
    public String updateNoticeSubmit(NoticeDto noticeDto) {
        noticeService.updateNotice(noticeDto);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteNotice(NoticeDto noticeDto) {
         noticeService.deleteNotice(noticeDto);
        return "redirect:list";
    }

    @GetMapping("/information")
    public String information() {
        return "notice/information";
    }

}
