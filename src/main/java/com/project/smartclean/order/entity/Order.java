package com.project.smartclean.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.smartclean.admin.model.PickupCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "orders")
@DynamicUpdate
public class Order implements PickupCode {
    @Id
    private String orderId;
    private String orderUserPhone;
    private String orderUserName;
    private String address1;
    private String address2;
    private LocalDateTime orderDate;
    //    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private String userId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime disposeDate;
    private Long districtCode;
    private String districtName;
    private String pickupStatus;

    //    @Enumerated(EnumType.STRING)
    private String orderStatus;



}
