package com.project.smartclean.order.dto;

import com.project.smartclean.admin.entity.Category;
import com.project.smartclean.admin.repository.CategoryRepository;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.entity.Item;
import lombok.*;

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
    private Category categoryName;


    public static ItemDto of(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setCategoryName(item.getCategoryName());
        return ItemDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                //.checkedYn(item.isCheckedYn())
                //.sortValue(item.getSortValue())
                .categoryName(item.getCategoryName())
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
