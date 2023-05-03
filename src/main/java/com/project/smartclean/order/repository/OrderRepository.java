package com.project.smartclean.order.repository;

import com.project.smartclean.order.entity.UserOrder;
import com.project.smartclean.order.model.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, String> {
//    @Query("select w from UserOrder w where w.wasteName = :name")
//    List<UserOrder> methodName(@Param("name") String wasteName);


//    List<UserOrder> findAll(OrderForm parameter);
}
