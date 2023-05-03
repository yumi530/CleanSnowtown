package com.project.smartclean.order.dto;

import com.project.smartclean.order.entity.Order;
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
public class OrderDto {
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


    public static OrderDto of(Order order) {

        return OrderDto.builder()
                .orderUserName(order.getOrderUserName())
                .districtName(order.getDistrictName())
                .districtCode(order.getDistrictCode())
                .disposeDate(order.getDisposeDate())
                .orderDate(order.getOrderDate())
                .address1(order.getAddress1())
                .address2(order.getAddress2())
                .orderUserPhone(order.getOrderUserPhone())
//                .itemId(userOrder.getItems().get())
                .pickupStatus(order.getPickupStatus())
                .build();
    }

}
