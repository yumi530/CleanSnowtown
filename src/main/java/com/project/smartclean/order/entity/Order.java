package com.project.smartclean.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "orders")
@DynamicUpdate
public class Order {
    @Id @GeneratedValue
    private String orderId;
    private String orderUserPhone;
    private String orderUserName;
    private String address1;
    private String address2;
    private LocalDateTime orderDate;
    //    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private String userId;
    @DateTimeFormat
    private LocalDateTime disposeDate;
    private Long districtCode;
    private String districtName;
    private String pickupStatus;

    //    @Enumerated(EnumType.STRING)
    private String orderStatus;



}
