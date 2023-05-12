package com.project.smartclean.order.service;

import com.project.smartclean.member.entity.Search;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order order(OrderForm parameter);

    List<ItemDto> wasteFrontList(Item item);

    List<Order> wastesOrder();

    OrderDto orderDetail(String orderId);

    boolean pickupStatus(String orderNumber, String pickupStatus);

    OrderDto detail(String userId);

    List<Order> getOrderList();

    Page<Order> getOrderList(Search search, Pageable pageable);
}
