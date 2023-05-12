package com.project.smartclean.admin.service;

//import com.project.smartclean.admin.model.WasteCategoryInput;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.ItemDto;

import java.util.List;

public interface AdminCategoryService {


    List<ItemDto>list();

    void addWaste(ItemDto parameter);

    void deleteWaste(ItemDto parameter);

    void updateWaste(ItemDto parameter);

    List<DistrictsDto> districtList();

    void addDistrict(DistrictsDto parameter);

    void updateDistrict(DistrictsDto parameter);

    void deleteDistrict(Long districtsCode);
}

