package com.project.smartclean.admin.entity;

import com.project.smartclean.order.entity.Item;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private String category;



    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();


//    private Long categoryId;
//    private String categoryName;


//    @ManyToMany
//    @JoinTable(name = "categoryItem")
//    private List<Item> items = new ArrayList<>();
}
