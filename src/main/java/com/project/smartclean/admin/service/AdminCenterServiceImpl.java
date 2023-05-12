//package com.project.smartclean.admin.service;
//
//import com.project.smartclean.common.utils.CommonUtils;
//import com.project.smartclean.member.entity.Search;
//import com.project.smartclean.order.dto.ItemDto;
//import com.project.smartclean.order.dto.OrderDto;
//import com.project.smartclean.order.entity.Item;
//import com.project.smartclean.order.entity.Order;
//import com.project.smartclean.order.entity.QOrder;
//import com.project.smartclean.order.model.OrderForm;
//import com.project.smartclean.order.repository.OrderRepository;
//import com.project.smartclean.order.repository.ItemRepository;
//import com.querydsl.core.BooleanBuilder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AdminCenterServiceImpl {
//    private final OrderRepository orderRepository;
//    private final ItemRepository itemRepository;
//
//    @Override
//    public Order order(OrderForm parameter) {
//        String orderId = CommonUtils.getOrderUUID();
//
//        Order order = Order.builder()
//                .orderId(orderId)
//                .userId(parameter.getUserId())
//                .orderUserName(parameter.getOrderUserName())
//                .orderUserPhone(parameter.getOrderUserPhone())
//                .address1(parameter.getAddress1())
//                .address2(parameter.getAddress2())
//                .disposeDate(parameter.getDisposeDate())
//                .orderDate(LocalDateTime.now())
//                .districtCode(parameter.getDistrictCode())
//                .districtName(parameter.getDistrictName())
//                .pickupStatus(Order.PICKUP_STATUS_WAITING)
//                .orderStatus(Order.ORDER_COMPLETE)
//                .build();
//
//        orderRepository.save(order);
//
//        return order;
//    }
//
//    @Override
//    public List<ItemDto> wasteFrontList(Item item) {
//        List<Item> list = itemRepository.findAll();
//        return ItemDto.of(list);
//    }
//
//
//    @Override
//    public List<Order> itemOrder() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    public List<Order> getOrderList() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    public Page<Order> getOrderList(Search search, Pageable pageable) {
//        BooleanBuilder builder = new BooleanBuilder();
//        QOrder qOrder = QOrder.order;
//        if (search.getSearchCondition().equals("districtName")) {
//            builder.and(qOrder.districtName.like("%" + search.getSearchKeyword() + "%"));
//        }else if (search.getSearchCondition().equals("orderId")) {
//            builder.and(qOrder.orderId.like("%" + search.getSearchKeyword() + "%"));
//        }
//        if (search.getSearchCondition().equals("orderUserName")) {
//            builder.and(qOrder.orderUserName.like("%" + search.getSearchKeyword() + "%"));
//        } else if (search.getSearchCondition().equals("orderUserPhone")) {
//            builder.and(qOrder.orderUserPhone.like("%" + search.getSearchKeyword() + "%"));
//        }
//        return orderRepository.findAll(builder, pageable);
//    }
//
//
//    @Override
//    public OrderDto orderDetail(String orderId) {
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if (!optionalOrder.isPresent()) {
//            return null;
//        }
//        Order detail = optionalOrder.get();
//        return OrderDto.of(detail);
//    }
//
//    @Override
//    public boolean pickupStatus(String orderId, String pickupStatus) {
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if (!optionalOrder.isPresent()) {
//            throw new RuntimeException("주문 정보가 존재하지 않습니다.");
//        }
//        Order order = optionalOrder.get();
//        order.setPickupStatus(pickupStatus);
//        orderRepository.save(order);
//        return true;
//    }
//}
