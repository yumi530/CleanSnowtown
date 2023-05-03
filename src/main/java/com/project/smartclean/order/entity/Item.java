package com.project.smartclean.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long itemId;
    //private Long districtCode;
    private String itemName;
    private Long price;
    private int count;
    private String itemDetail;


//    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
//    private List<Item> items = new ArrayList<>();
//살려줌
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private UserOrder userOrder;


//@OneToOne
//@JoinColumn(name = "user_order_id")
//private UserOrder userOrder;

////    private boolean checkedYn;
////
////    private int sortValue;

//    private List<Category> categories = new ArrayList<>();



}
