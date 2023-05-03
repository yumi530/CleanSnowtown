package com.project.smartclean.admin.service;

//import com.project.smartclean.admin.model.WasteCategoryInput;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.WasteDto;

import java.util.List;

public interface AdminCategoryService {


    List<WasteDto>list();

    void addWaste(WasteDto parameter);

    void deleteWaste(WasteDto parameter);

    void updateWaste(WasteDto parameter);

    List<DistrictsDto> districtList();

    void addDistrict(DistrictsDto parameter);

    void updateDistrict(DistrictsDto parameter);

    void deleteDistrict(Long districtsCode);
}

