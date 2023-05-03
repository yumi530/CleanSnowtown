package com.project.smartclean.order.service;

import com.project.smartclean.order.dto.UserOrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.UserOrder;
import com.project.smartclean.order.model.OrderForm;

import java.util.List;

public interface OrderService {
    UserOrder order(OrderForm parameter);

    List<WasteDto> wasteFrontList(Item item);

    List<UserOrder> wastesOrder(OrderForm parameter);

    UserOrderDto userOrderDetail(String orderId);

    boolean pickupStatus(String orderNumber, String pickupStatus);

    UserOrderDto detail(String userId);
}
