package com.project.smartclean.order.entity;

import com.project.smartclean.admin.entity.Category;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "category_name")
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
    @JoinColumn(name = "category_name")
    private Category category;

}
