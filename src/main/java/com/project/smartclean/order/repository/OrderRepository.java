package com.project.smartclean.order.repository;

import com.project.smartclean.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>, QuerydslPredicateExecutor<Order> {
    List<Order> findAll();
}
