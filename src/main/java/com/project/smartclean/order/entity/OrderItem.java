//package com.project.smartclean.order.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "order_item")
//@Data
//public class OrderItem {
//    @Id
//    @GeneratedValue
//    @Column(name = "order_item_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_number")
//    private UserOrder userOrder;
//
////    private Long price;
////    private int count;
//}
