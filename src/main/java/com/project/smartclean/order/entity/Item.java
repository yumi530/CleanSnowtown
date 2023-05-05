package com.project.smartclean.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long itemId;
    private int count;
    private Long price;
    private String itemDetail;
    private String itemName;

}
