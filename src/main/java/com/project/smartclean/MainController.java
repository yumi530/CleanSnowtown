package com.project.smartclean;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {
    private MemberService memberService;
    @GetMapping("/")
    public String index(@AuthenticationPrincipal Member member) {
        return "index";
    }

    @GetMapping("/admin.do")
    public String main() {
        return "/admin/main";
    }
}
