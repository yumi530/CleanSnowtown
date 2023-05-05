package com.project.smartclean.admin.service;

import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;

import java.util.List;

public interface AdminCenterService  {
    Order wasteOrder(OrderForm parameter);
    List<WasteDto> wasteFrontList(Item item);
    List<Order> wastesOrder(OrderForm parameter);

    OrderDto orderDetail(String orderId);

    boolean pickupStatus(String orderId, String pickupStatus);
}
