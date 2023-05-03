package com.project.smartclean.admin.controller;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.dto.WasteDto;
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
@RequestMapping("/admin/center")
public class AdminCenterController {
    private final OrderService orderService;


    @GetMapping("/order/waste.do")
    public String waste(Model model, Item item) {
        List<WasteDto> list = orderService.wasteFrontList(item);
        model.addAttribute("list", list);
        return "admin/center/waste";
    }

//    @GetMapping("/waste.do")
//    public String getAllList(Model model,  Pageable pageable) {
//        Page<Item> wastePage = orderService.wasteFrontList(pageable);
//
//        //페이지블럭 처리
//        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
//        int nowPage = wastePage.getPageable().getPageNumber() + 1;
//        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 9, getAllList.getTotalPages());
//
////                if (search.getSearchKeyword() == null)
////            search.setSearchKeyword("");
////        Page<Item> getWasteList = orderService.getWasteList(search);
////
////        model.addAttribute("list", getWasteList);
//
//
//        model.addAttribute("list", getAllList);
//
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "admin/center/waste";
//    }


    @PostMapping("/waste.do")
    public String orderWaste(Model model, OrderForm parameter, Member member, Item... items) {
        Order orderWaste = orderService.order(parameter);
        model.addAttribute("order", orderWaste);
        model.addAttribute("resultList", parameter.getResultList());
//        parameter.getResultList();
        return "order/confirm";
    }

    @PostMapping("/waste_complete.do")
    public String wasteComplete() {
        return "order/complete";
    }

}
