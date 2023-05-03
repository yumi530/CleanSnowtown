package com.project.smartclean.admin.controller;

import com.project.smartclean.admin.service.AdminCategoryService;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.WasteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final AdminCategoryService categoryService;

    @GetMapping("/admin/waste/list.do")
    public String list(Model model, WasteDto parameter) {
        List<WasteDto> list = categoryService.list();
        model.addAttribute("list", list);
        return "admin/waste/list";
    }

    @PostMapping("/admin/waste/add.do")
    public String addList(WasteDto parameter) {
        categoryService.addWaste(parameter);
        return "redirect:/admin/waste/list.do";
    }

    @PostMapping("/admin/waste/delete.do")
    public String deleteWaste(WasteDto parameter) {
        categoryService.deleteWaste(parameter);
        return "redirect:/admin/waste/list.do";
    }

    @PostMapping("/admin/waste/update.do")
    public String updateWaste(WasteDto parameter) {
        categoryService.updateWaste(parameter);
        return "redirect:/admin/waste/list.do";
    }


    @GetMapping("/admin/district/list.do")
    public String list(Model model, DistrictsDto parameter) {

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
        return "redirect:/admin/waste/list.do";
    }

    @PostMapping("/admin/district/update.do")
    public String updateDistrict(DistrictsDto parameter) {
        categoryService.updateDistrict(parameter);
        return "redirect:/admin/waste/list.do";
    }


}