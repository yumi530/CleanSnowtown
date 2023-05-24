package com.project.smartclean.order.controller;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }

    @GetMapping("/item")
    public String getAllList(Model model, Item item) {
        List<ItemDto> list = orderService.wasteFrontList(item);
        model.addAttribute("list", list);
        return "order/item";
    }

    @PostMapping("/item")
    public String orderItem(Model model, OrderForm parameter, @AuthenticationPrincipal Member member) {
        Order orderItem = orderService.order(parameter, member);
        model.addAttribute("order", orderItem);
        model.addAttribute("resultList", parameter.getResultList());
//        parameter.getResultList();
        return "order/confirm";
    }

//원래 버전
//    @GetMapping("/detail")
//    public String orderDetail(Model model, @AuthenticationPrincipal Member member) {
////        member.getUserId();
//        List<OrderDto> orderDto = orderService.detail(member.getUsername());
//        model.addAttribute("order", orderDto);
//        return "order/detail";
//    }
        @GetMapping("/detail")
    public String orderDetail(Model model, @AuthenticationPrincipal Member member, Order order) {
//        member.getUserId();
        List<OrderDto> orderDto = orderService.detail(member.getUsername(), order);
        model.addAttribute("order", orderDto);
        return "order/detail";
    }

}
