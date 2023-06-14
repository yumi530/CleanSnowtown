package com.project.smartclean.order.controller;

import com.project.smartclean.board.dto.CommentDto;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
////        member.getUser

    @GetMapping("/list")
    public String list(Model model, @AuthenticationPrincipal Member member) {
        List<OrderDto> list = orderService.detailList(member.getUserId());
        model.addAttribute("list", list);
        return "order/list";
    }

    @GetMapping("/detail/{orderId}")
    public String detail(Model model, @PathVariable String orderId) {
        OrderDto order = orderService.detail(orderId);
        model.addAttribute("order", order);
        return "order/detail";
    }
    @PostMapping("/cancel")
    public ResponseEntity cancel(@ModelAttribute String orderId){
    OrderDto orderDto = orderService.cancel(orderId);
    return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

}