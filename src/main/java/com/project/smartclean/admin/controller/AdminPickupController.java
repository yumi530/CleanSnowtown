package com.project.smartclean.admin.controller;

import com.project.smartclean.order.dto.OrderDto;
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
@RequestMapping("/admin/pickup")
public class AdminPickupController {
    private final OrderService orderService;
    @GetMapping("/list.do")
    public String pickupList(Model model) {
        List<Order> orderList = orderService.getOrderList();
        model.addAttribute("list",orderList);
        return "admin/pickup/list";
    }

//    @GetMapping("/detail")
//    public String pickupDetail(Model model,HttpServletRequest httpServletRequest) {
////        Optional<UserOrder> order = orderService.orderDetail(orderNumber);
////        model.addAttribute("detail", order);
//            String orderNumber = httpServletRequest.getParameter("orderNumber");
//            Optional<UserOrder> result = orderService.orderDetail(orderNumber);
//            model.addAttribute("result", result);
//        return "admin/pickup/detail"+ orderNumber;
//    }
// 밑으로 살려
    @GetMapping("/detail.do")
    public String pickupDetail(Model model,OrderForm parameter) {
        OrderDto detail = orderService.orderDetail(parameter.getOrderId());
        model.addAttribute("detail", detail);
        return "admin/pickup/detail";
    }

    @PostMapping("/status.do")
    public String status(Model model, OrderForm parameter) {
        boolean result = orderService.pickupStatus(parameter.getOrderId(), parameter.getPickupStatus());
        model.addAttribute("result", result);
        return "redirect:/admin/pickup/list.do";
    }
}
