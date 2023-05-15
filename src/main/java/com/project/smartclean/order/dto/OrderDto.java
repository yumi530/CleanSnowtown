package com.project.smartclean.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.smartclean.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String orderId;
    private String orderUserName;
    private String orderUserPhone;
    private String userId;
    private String itemName;
    private String address1;
    private String address2;
    private String disposeDate;
    private LocalDateTime orderDate;
    private Long districtCode;
    private String districtName;
    private String pickupStatus;
    private String orderStatus;


    public static OrderDto of(Order order) {

        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderUserName(order.getOrderUserName())
                .districtName(order.getDistrictName())
                .districtCode(order.getDistrictCode())
                .disposeDate(order.getDisposeDate())
                .orderDate(order.getOrderDate())
                .address1(order.getAddress1())
                .address2(order.getAddress2())
                .orderUserPhone(order.getOrderUserPhone())
                .pickupStatus(order.getPickupStatus())
                .orderStatus(order.getOrderStatus())
                .build();
    }

}
