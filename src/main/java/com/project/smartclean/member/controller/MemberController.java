package com.project.smartclean.member.controller;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.model.ResetPasswordInput;
import com.project.smartclean.member.model.ServiceResult;
import com.project.smartclean.member.model.SignUpForm;
import com.project.smartclean.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/register")
    public String registerSubmit(Model model, SignUpForm parameter) {
        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);
        return "member/register_complete";
    }

    @GetMapping("/verify")
    public String verify(Model model, HttpServletRequest httpServletRequest) {
        String uuid = httpServletRequest.getParameter("id");

        boolean result = memberService.verify(uuid);
        model.addAttribute("result", result);
        return "member/verify";
    }

    @RequestMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/find_password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/find_password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {
        boolean result = memberService.sendResetPassword(parameter);
        model.addAttribute("result", result);
        return "member/find_password_result";
    }

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal Member member, Model model) {
        MemberDto memberDto = memberService.detail(member.getUsername());
        model.addAttribute("member", memberDto);
        return "member/mypage";
    }

    @GetMapping("/password")
    public String memberPassword(Model model, Principal principal) {

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/password";
    }

    @PostMapping("/password")
    public String memberPasswordSubmit(Model model
            , SignUpForm parameter
            , Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/mypage";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model, @RequestParam("id") String userId) {
        model.addAttribute("userId", userId);
        return "member/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawSubmit(Model model, SignUpForm parameter) {
        
        MemberDto memberDto = memberService.detail(parameter.getUserId());
        ServiceResult result = memberService.withdraw(parameter.getUserId(), memberDto.getPassword());

        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/logout";
    }
}



