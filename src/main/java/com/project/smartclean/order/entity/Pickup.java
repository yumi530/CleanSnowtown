//package com.project.smartclean.order.entity;
//
//import com.project.smartclean.admin.model.PickupCode;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Pickup {
//    @Id
//    @GeneratedValue
//    @Column(name = "pickup_id")
//    private Long id;
//
//    @OneToOne(mappedBy = "pickup", fetch = FetchType.LAZY)
//    private UserOrder userOrder;
//
//    @Enumerated(EnumType.STRING)
//    private PickupStatus status;
//
//
//}
