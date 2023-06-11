package com.project.smartclean.admin.controller;

import com.project.smartclean.admin.entity.Category;
import com.project.smartclean.admin.service.AdminCategoryService;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Districts;
import com.project.smartclean.order.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final AdminCategoryService categoryService;

    @GetMapping("/admin/item/list.do")
    public String list(Model model) {
        List<ItemDto> list = categoryService.list();
        model.addAttribute("list", list);
        return "admin/item/list";
    }

    @PostMapping("/admin/item/add.do")
    public String addList(ItemDto parameter) {
        categoryService.addWaste(parameter);
        return "redirect:/admin/item/list.do";
    }

    @PostMapping("/admin/item/delete.do")
    public String deleteWaste(ItemDto parameter) {
        categoryService.deleteWaste(parameter);
        return "redirect:/admin/item/list.do";
    }

    @PostMapping("/admin/item/update.do")
    public ResponseEntity updateItem(@ModelAttribute ItemDto parameter) {
        Item result = categoryService.updateWaste(parameter);
        if (result != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }


    @GetMapping("/admin/district/list.do")
    public String districtList(Model model) {
        List<DistrictsDto> list = categoryService.districtList();
        model.addAttribute("list", list);
        return "admin/district/list";
    }

    @PostMapping("/admin/district/add.do")
    public String addDistrictList(DistrictsDto parameter) {
        categoryService.addDistrict(parameter);
        return "redirect:/admin/district/list.do";
    }

    @PostMapping("/admin/district/delete.do")
    public String deleteDistrict(Long districtCode) {
        categoryService.deleteDistrict(districtCode);
        return "redirect:/admin/district/list.do";
    }

    @PostMapping("/admin/district/update.do")
    public ResponseEntity updateDistrict(@ModelAttribute DistrictsDto parameter) {
        Districts result = categoryService.updateDistrict(parameter);
        if (result != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }
}