package com.project.smartclean.order.service;

import com.project.smartclean.common.utils.CommonUtils;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.repository.ItemRepository;
import com.project.smartclean.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Override
    public Order order(OrderForm parameter) {
        String orderId = CommonUtils.getOrderUUID();

        Order order = Order.builder()
                .orderId(orderId)
                .userId(parameter.getUserId())
                .orderUserName(parameter.getOrderUserName())
                .orderUserPhone(parameter.getOrderUserPhone())
                .address1(parameter.getAddress1())
                .address2(parameter.getAddress2())
                .disposeDate(parameter.getDisposeDate())
                .orderDate(LocalDateTime.now())
                .districtName(parameter.getDistrictName())
                .build();

        orderRepository.save(order);

        return order;
    }

    @Override
    public List<WasteDto> wasteFrontList(Item item) {
        List<Item> list = itemRepository.findAll();
        return WasteDto.of(list);
    }


    @Override
    public List<Order> wastesOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDto orderDetail(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            return null;
        }
        Order detail = optionalOrder.get();
        return OrderDto.of(detail);
    }

    @Override
    public boolean pickupStatus(String orderNumber, String pickupStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderNumber);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("주문 정보가 존재하지 않습니다.");
        }
        Order order = optionalOrder.get();
        order.setPickupStatus(pickupStatus);
        orderRepository.save(order);
        return true;
    }

    @Override
    public OrderDto detail(String userId) {
        Optional<Order> optionalOrder = orderRepository.findById(userId);
        if (!optionalOrder.isPresent()) {
            return null;
        }
        Order order = optionalOrder.get();

        return OrderDto.of(order);
    }
}
