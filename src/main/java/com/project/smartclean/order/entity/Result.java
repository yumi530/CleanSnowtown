//package com.project.smartclean.order.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//public class Result {
//    @Id
//    @GeneratedValue
//    @Column(name = "result_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_order_id")
//    private UserOrder userOrder;
//
////    private String wasteName;
//    private int count;
//    private Long price;
////    private String wasteDetail;
//}
