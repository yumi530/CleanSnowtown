package com.project.smartclean.admin.service;

import com.project.smartclean.order.dto.UserOrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.UserOrder;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.model.OrderForm;

import java.util.List;

public interface AdminCenterService  {
    UserOrder wasteOrder(OrderForm parameter);
    List<WasteDto> wasteFrontList(Item item);
    List<UserOrder> wastesOrder(OrderForm parameter);

    UserOrderDto userOrderDetail(String orderNumber);

    boolean pickupStatus(String orderNumber, String pickupStatus);
}
