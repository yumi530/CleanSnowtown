package com.project.smartclean.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.smartclean.admin.model.PickupCode;
import com.project.smartclean.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
@DynamicUpdate
@ToString(exclude = "user_id")
public class Order implements PickupCode {
    @Id
    private String orderId;
    private String orderUserPhone;
    private String orderUserName;
    private String address1;
    private String address2;
    private LocalDateTime orderDate;
    //    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "user_id")
//    private String userId;
    private String disposeDate;
    private Long districtCode;
    private String districtName;
    private String pickupStatus;

    //    @Enumerated(EnumType.STRING)
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Member member;



}
