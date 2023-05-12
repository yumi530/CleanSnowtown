package com.project.smartclean.order.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    @GetMapping("/item")
    public String getAllList(Model model, Item item) {
        List<ItemDto> list = orderService.wasteFrontList(item);
        model.addAttribute("list", list);
        return "order/item";
    }

    @PostMapping("/item")
    public String orderItem(Model model, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")OrderForm parameter) {
        Order orderItem = orderService.order(parameter);
        model.addAttribute("order", orderItem);
        model.addAttribute("resultList", parameter.getResultList());
//        parameter.getResultList();
        return "order/confirm";
    }


    @GetMapping("/detail")
    public String orderDetail(Model model, OrderForm parameter, String userId) {
        OrderDto orderDto = orderService.orderDetail(parameter.getOrderId());
        model.addAttribute("order", orderDto);
        return "order/detail";
    }

}
