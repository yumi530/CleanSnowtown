package com.project.smartclean.admin.controller;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/center")
public class AdminCenterController {
    private final OrderService orderService;

    @GetMapping("/item.do")
    public String item(Model model, Item item) {
        List<ItemDto> list = orderService.wasteFrontList(item);
        model.addAttribute("list", list);
        return "center/item";
    }

    @PostMapping("/item.do")
    public String orderItem(Model model, OrderForm parameter, Member member) {
        Order orderItem = orderService.order(parameter, member);
        model.addAttribute("order", orderItem);
        model.addAttribute("resultList", parameter.getResultList());
//        parameter.getResultList();
        return "center/confirm";
    }
    
}
