package com.project.smartclean.order.dto;

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
public class WasteDto {
    private Long itemId;
    //    private Long districtCode;
    private String itemName;
    private Long price;
    private String itemDetail;
//    private int sortValue;
//    private boolean checkedYn;



    public static WasteDto of(Item item) {

        return WasteDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                //.checkedYn(item.isCheckedYn())
                //.sortValue(item.getSortValue())
                .build();
    }

    public static List<WasteDto> of(List<Item> items) {

        if (items == null) {
            return null;
        }

        List<WasteDto> wasteDtoList = new ArrayList<>();
        for (Item x : items) {
            wasteDtoList.add(WasteDto.of(x));
        }
        return wasteDtoList;
    }

}
