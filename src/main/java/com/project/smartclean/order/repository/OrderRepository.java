package com.project.smartclean.order.repository;

import com.project.smartclean.member.entity.Member;
import com.project.smartclean.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>, QuerydslPredicateExecutor<Order> {
    List<Order> findAll();
    List<Order> findAllByMember_UserId(String userId);
}
