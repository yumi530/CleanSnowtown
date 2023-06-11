package com.project.smartclean.admin.service;

//import com.project.smartclean.admin.model.WasteCategoryInput;

import com.project.smartclean.admin.entity.Category;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Districts;
import com.project.smartclean.order.entity.Item;

import java.util.List;

public interface AdminCategoryService {


    List<ItemDto> list();

    void addWaste(ItemDto parameter);

    void deleteWaste(ItemDto parameter);

    Item updateWaste(ItemDto parameter);

    List<DistrictsDto> districtList();

    void addDistrict(DistrictsDto parameter);

    Districts updateDistrict(DistrictsDto parameter);

    void deleteDistrict(Long districtsCode);

    List<ItemDto> findAll(Long itemId);
}

