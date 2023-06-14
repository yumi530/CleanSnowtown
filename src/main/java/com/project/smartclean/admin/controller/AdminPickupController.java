package com.project.smartclean.admin.controller;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.entity.Search;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String pickupList(Model model, @PageableDefault(page =  0 , size = 10, sort ="orderDate", direction = Sort.Direction.DESC) Pageable pageable, Search search) {

        if (search.getSearchCondition() == null)
            search.setSearchCondition("districtName");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");
        Page<Order> orderList = orderService.getOrderList(search, pageable);

        int nowPage = orderList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 , 1);
        int endPage = Math.min(nowPage + 5, orderList.getTotalPages());

        model.addAttribute("list", orderList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
// 밑으로 살려 다시 확인
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
