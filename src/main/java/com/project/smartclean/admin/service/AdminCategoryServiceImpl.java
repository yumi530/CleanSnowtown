package com.project.smartclean.admin.service;

import com.project.smartclean.admin.entity.Category;
import com.project.smartclean.admin.repository.CategoryRepository;
import com.project.smartclean.order.dto.DistrictsDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Districts;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.repository.DistrictsRepository;
import com.project.smartclean.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final DistrictsRepository districtsRepository;


    @Override
    public List<ItemDto> list() {
        List<Item> item = itemRepository.findAll();
        return ItemDto.of(item);
    }

    @Override
    public void addWaste(ItemDto parameter) {
        Item newItem = Item.builder()
                .itemId(parameter.getItemId())
                .categoryName(parameter.getCategoryName())
                .itemName(parameter.getItemName())
                .itemDetail(parameter.getItemDetail())
                .price(parameter.getPrice())
                .build();
        itemRepository.save(newItem);
    }

    @Override
    public void deleteWaste(ItemDto parameter) {

        itemRepository.deleteById(parameter.getItemId());

    }

    @Override
    public Item updateWaste(ItemDto parameter) {
        Item item = new Item();
        item.setItemId(parameter.getItemId());
        item.setCategoryName(parameter.getCategoryName());
        item.setItemName(parameter.getItemName());
        item.setItemDetail(parameter.getItemDetail());
        item.setPrice(parameter.getPrice());
        Item result = itemRepository.save(item);
        return result;
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
    public Districts updateDistrict(DistrictsDto parameter) {
        Districts districts = new Districts();
        districts.setDistrictCode(parameter.getDistrictCode());
        districts.setDistrictName(parameter.getDistrictName());
        Districts result = districtsRepository.save(districts);
        return result;
    }

    @Override
    public void deleteDistrict(Long districtsCode) {
        districtsRepository.deleteById(districtsCode);
    }

    @Override
    public List<ItemDto> findAll(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        List<Item> itemList = itemRepository.findAllByItemId(item);
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item x : itemList) {
            ItemDto itemDto = ItemDto.of(x);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
}


