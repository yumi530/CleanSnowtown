package com.project.smartclean.order.repository;

import com.project.smartclean.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
//    @Query("select w from UserOrder w where w.wasteName = :name")
//    List<UserOrder> methodName(@Param("name") String wasteName);


//    List<UserOrder> findAll(OrderForm parameter);
}
