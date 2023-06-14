package com.project.smartclean.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "item_id")
    private Long itemId;
    private int count;
    private Long price;


}
