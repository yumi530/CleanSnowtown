package com.project.smartclean.admin.service;

import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Districts;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.repository.DistrictsRepository;
import com.project.smartclean.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final ItemRepository itemRepository;
    private final DistrictsRepository districtsRepository;



    @Override
    public List<WasteDto>list() {
        List<Item> items = itemRepository.findAll();
        return WasteDto.of(items);
    }

    @Override
    public void addWaste(WasteDto parameter) {
        Item newItem = Item.builder()
                .itemId(parameter.getItemId())
                .category(parameter.getCategory())
                .itemName(parameter.getItemName())
                .itemDetail(parameter.getItemDetail())
                .price(parameter.getPrice())
                .build();
        itemRepository.save(newItem);
    }

    @Override
    public void deleteWaste(WasteDto parameter) {

        itemRepository.deleteById(parameter.getItemId());

    }

    @Override
    public void updateWaste(WasteDto parameter) {
        Optional<Item> optionalItem = itemRepository.findById(parameter.getItemId());
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setCategory(parameter.getCategory());
            item.setItemName(parameter.getItemName());
            item.setItemDetail(parameter.getItemDetail());
            item.setPrice(parameter.getPrice());
            itemRepository.save(item);
        }

    }

    @Override
    public List<DistrictsDto> districtList() {
        List<Districts> districts = districtsRepository.findAll();
        return DistrictsDto.of(districts);
    }

    @Override
    public void addDistrict(DistrictsDto parameter) {
        Districts newDistricts = Districts.builder()
                .districtCode(parameter.getDistrictCode())
                .districtName(parameter.getDistrictName())
                .build();
        districtsRepository.save(newDistricts);

    }

    @Override
    public void updateDistrict(DistrictsDto parameter) {
        Optional<Districts> optionalDistricts = districtsRepository.findById(parameter.getDistrictCode());
        if (optionalDistricts.isPresent()) {
            Districts districts = optionalDistricts.get();
            districts.setDistrictName(parameter.getDistrictName());
            districtsRepository.save(districts);
        }
    }

    @Override
    public void deleteDistrict(Long districtsCode) {
       districtsRepository.deleteById(districtsCode);
    }
}
