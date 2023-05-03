//package com.project.smartclean.order.entity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import java.time.LocalDateTime;
//
//
//
//import com.project.smartclean.member.entity.Member;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class Order {
//    @Id
//    @GeneratedValue
//    private Long orderId;
//
//    private String orderNumber;
//    private String orderUserName;
//    //    private String orderUserId;
//    private String address1;
//    private String address2;
//    private String orderUserPhone;
//
//    private LocalDateTime disposeDate;
//    private LocalDateTime orderDate;
//    private Long districtCode;
//    private String districtName;
//    private Long itemId;
//    private String itemName;
//    private String pickupStatus;
//////    private String itemDetail;
////
////    @ManyToOne
////    @JoinColumn(name = "user_id")
////    private Member member;
////
////    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
////    private List<Item> item = new ArrayList<>();
//////@OneToOne(mappedBy = "user_order", cascade = CascadeType.ALL)
//////private Item item;
//////    @OneToOne(mappedBy = "userOrder", cascade = CascadeType.ALL)
//////    private Item item;
////
//////    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//////    @JoinColumn(name = "pickup_id")
//////    private Pickup pickup;
////
////    @Enumerated(EnumType.STRING)
////    private OrderStatus status;
////
////}
//
//}
