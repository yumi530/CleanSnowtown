package com.project.smartclean.order.dto;

import com.project.smartclean.admin.entity.Category;
import com.project.smartclean.order.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDto {
    private Long itemId;
    //    private Long districtCode;
    private String itemName;
    private Long price;
    private String itemDetail;
//    private int sortValue;
//    private boolean checkedYn;
    private Category category;



    public static ItemDto of(Item item) {

        return ItemDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                //.checkedYn(item.isCheckedYn())
                //.sortValue(item.getSortValue())
                .category(item.getCategory())
                .build();
    }

    public static List<ItemDto> of(List<Item> items) {

        if (items == null) {
            return null;
        }

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item x : items) {
            itemDtoList.add(ItemDto.of(x));
        }
        return itemDtoList;
    }

}
