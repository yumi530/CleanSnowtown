package com.project.smartclean.order.controller;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/")
    public String order() {
        return "order/page";
    }

    @GetMapping("/item")
    public String getAllList(Model model, Item item) {
        List<WasteDto> list = orderService.wasteFrontList(item);
        model.addAttribute("list", list);
        return "order/item";
    }

    @PostMapping("/item")
    public String orderWaste(Model model, OrderForm parameter) {
        Order orderWaste = orderService.order(parameter);
        model.addAttribute("order", orderWaste);
        model.addAttribute("resultList", parameter.getResultList());
//        parameter.getResultList();
        return "order/confirm";
    }

    @PostMapping("/waste_complete")
    public String wasteComplete() {
        return "order/complete";
    }

    @GetMapping("/detail")
    public String orderDetail(Model model, OrderForm parameter) {
        OrderDto orderDto = orderService.orderDetail(parameter.getOrderId());
        model.addAttribute("order", orderDto);
        return "order/detail";
    }

}
