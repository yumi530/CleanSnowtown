package com.project.smartclean.order.dto;

import com.project.smartclean.order.entity.UserOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDto {
    private String orderNumber;

    private String orderUserName;
    private String orderUserId;
    private String address1;
    private String address2;
    private String orderUserPhone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime disposeDate;
    private LocalDateTime orderDate;
    private Long districtCode;
    private String districtName;
    private Long itemId;
    private String itemName;

    private String pickupStatus;

    private String itemDetail;


    public static UserOrderDto of(UserOrder userOrder) {

        return UserOrderDto.builder()
                //.orderNumber(userOrder.getOrderNumber())
                //.orderUserId(userOrder.getOrderUserId())
                .orderUserName(userOrder.getOrderUserName())
                .districtName(userOrder.getDistrictName())
                .districtCode(userOrder.getDistrictCode())
                .disposeDate(userOrder.getDisposeDate())
                .orderDate(userOrder.getOrderDate())
                .address1(userOrder.getAddress1())
                .address2(userOrder.getAddress2())
                .orderUserPhone(userOrder.getOrderUserPhone())
//                .itemId(userOrder.getItems().get())
//                .itemName(userOrder.getItems().g)
//                .itemDetail(userOrder.getItems().getItemDetail())
                .pickupStatus(userOrder.getPickupStatus())
                .build();
    }

}
