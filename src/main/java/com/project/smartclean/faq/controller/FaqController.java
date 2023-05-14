package com.project.smartclean.faq.controller;


import com.project.smartclean.board.entity.Search;
import com.project.smartclean.faq.dto.FaqDto;
import com.project.smartclean.faq.entity.Faq;
import com.project.smartclean.faq.service.FaqService;
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

import javax.transaction.Transactional;

@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;
    @GetMapping("/list")
    public String faqList(Model model, @PageableDefault(page = 0, size = 10, sort = "faqNo", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("faqTirle");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        Page<Faq> list = faqService.getFaqList(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "faq/list";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/insert")
    public String insertFaq() {
        return "faq/insert";
    }

    @PostMapping("/insert")
    public String insertFaqSubmit(FaqDto faqDto, @AuthenticationPrincipal User user) {
        faqDto.setFaqWriteName(user.getUsername());
        faqService.insertFaq(faqDto);

        return "redirect:list";
    }

    @Transactional
    @GetMapping("/read")
    public String readFaq(Model model, Long faqNo) {
        FaqDto faqDto = faqService.readFaq(faqNo);
        model.addAttribute("faq", faqDto);
        return "faq/read";
    }


    @GetMapping("/update")
    public String updateFaq(Long faqNo, Model model) {
        FaqDto faqDto = faqService.readFaq(faqNo);
        model.addAttribute("faqUpdate", faqDto);
        return "faq/modify";
    }

    @PostMapping("/update")
    public String updateFaqSubmit(FaqDto faqDto) {
        faqService.updateFaq(faqDto);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteNotice(FaqDto faqDto) {
         faqService.deleteFaq(faqDto);
        return "redirect:list";
    }


}
