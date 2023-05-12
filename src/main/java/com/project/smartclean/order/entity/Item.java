package com.project.smartclean.order.entity;

import com.project.smartclean.admin.entity.Category;
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
    private int count;
    private Long price;
    private String itemDetail;
    private String itemName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

}
