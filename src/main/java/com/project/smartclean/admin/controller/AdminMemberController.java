package com.project.smartclean.admin.controller;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.admin.model.MemberInput;
import com.project.smartclean.admin.model.MemberParam;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.entity.Search;
import com.project.smartclean.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {
    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("userId");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");
        Page<Member> list = memberService.list(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter) {
//        parameter.init();
        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member", member);
        return "admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(MemberInput parameter) {
//        boolean result = memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String password(MemberInput parameter) {
//        boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getPassword());
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
}
