//package com.project.smartclean.order.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//public class Category {
//    @Id
//    @GeneratedValue
//    private Long categoryId;
//
//    private String categoryName;
//
//
//    @ManyToMany
//    @JoinTable(name = "categoryItem")
//    private List<Item> items = new ArrayList<>();
//}
