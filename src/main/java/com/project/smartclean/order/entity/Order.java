package com.project.smartclean.order.entity;

import com.project.smartclean.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@DynamicUpdate
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private String orderId;
    private String orderUserName;
    private String orderUserPhone;
    private String address1;
    private String address2;
    @DateTimeFormat
    private LocalDateTime disposeDate;
    private LocalDateTime orderDate;
    private Long districtCode;
    private String districtName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    private String pickupStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //    @OneToMany
//    @JoinColumn(name = "item_id")
//    private Item itemId;

//    private String itemName;
//    private String itemDetail;

//@OneToOne(mappedBy = "userOrder", cascade = CascadeType.ALL)
//private Item item;
//    @OneToOne(mappedBy = "userOrder", cascade = CascadeType.ALL)
//    private Item item;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "pickup_id")
//    private Pickup pickup;

}
