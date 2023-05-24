package com.project.smartclean.order.service;

import com.project.smartclean.common.utils.CommonUtils;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.entity.Search;
import com.project.smartclean.member.repository.MemberRepository;
import com.project.smartclean.order.dto.OrderDto;
import com.project.smartclean.order.dto.ItemDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.Order;
import com.project.smartclean.order.entity.QOrder;
import com.project.smartclean.order.model.OrderForm;
import com.project.smartclean.order.repository.ItemRepository;
import com.project.smartclean.order.repository.OrderRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.project.smartclean.order.dto.OrderDto.of;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public Order order(OrderForm parameter, Member member) {
        String orderId = CommonUtils.getOrderUUID();
        Member memberId = memberRepository.findById(member.getUsername()).get();

        Order order = Order.builder()
                .orderId(orderId)
                .member(memberId)
                .orderUserName(parameter.getOrderUserName())
                .orderUserPhone(parameter.getOrderUserPhone())
                .address1(parameter.getAddress1())
                .address2(parameter.getAddress2())
                .disposeDate(parameter.getDisposeDate())
                .orderDate(LocalDateTime.now())
                .districtCode(parameter.getDistrictCode())
                .districtName(parameter.getDistrictName())
                .pickupStatus(Order.PICKUP_STATUS_WAITING)
                .orderStatus(Order.ORDER_COMPLETE)
                .build();


        orderRepository.save(order);

        return order;
    }

    @Override
    public List<ItemDto> wasteFrontList(Item item) {
        List<Item> list = itemRepository.findAll();
        return ItemDto.of(list);
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
    public Page<Order> getOrderList(Search search, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QOrder qOrder = QOrder.order;
        if (search.getSearchCondition().equals("districtName")) {
            builder.and(qOrder.districtName.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("orderId")) {
            builder.and(qOrder.orderId.like("%" + search.getSearchKeyword() + "%"));
        }
        if (search.getSearchCondition().equals("orderUserName")) {
            builder.and(qOrder.orderUserName.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("orderUserPhone")) {
            builder.and(qOrder.orderUserPhone.like("%" + search.getSearchKeyword() + "%"));
        }
        return orderRepository.findAll(builder, pageable);
    }


//    @Override
//    public OrderDto orderDetail(String orderId) {
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if (!optionalOrder.isPresent()) {
//            return null;
//        }
//        Order detail = optionalOrder.get();
//        return OrderDto.of(detail);
//    }

    @Override
    public OrderDto orderDetail(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            return null;
        }
        Order detail = optionalOrder.get();
        return of(detail);
    }

    @Override
    public boolean pickupStatus(String orderId, String pickupStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("주문 정보가 존재하지 않습니다.");
        }
        Order order = optionalOrder.get();
        order.setPickupStatus(pickupStatus);
        orderRepository.save(order);
        return true;
    }



    //    @Override
//    public List<Order> detail(String userId) {
//        List<Order> orderList = orderRepository.findByMember_UserId(userId).get();
////        if (!optionalOrder.isPresent()) {
////            return null;
////        }
////        List<Order> order = optionalOrder.get();
//    }
    //다시
    //        if (!optionalOrder.isPresent()) {
//            throw new RuntimeException("주문 정보가 존재하지 않습니다.");
//        }
//        Order order = optionalOrder.get();
//        orders.setOrderId(order.getOrderId());
//        orders.setOrderUserName(order.getOrderUserName());
//        orders.setAddress1(order.getAddress1());
//        orders.setAddress2(order.getAddress2());
//        orders.setMember(memberId);
//        orders.setOrderDate(order.getOrderDate());
//        orders.setDisposeDate(order.getDisposeDate());
//        orders.setPickupStatus(order.getPickupStatus());
////        order.setOrderStatus(parameter.ge);
//
//
//        orderRepository.save(orders);
//
//        return (List<Order>) orders;

    @Override
    public List<OrderDto> detail(String userId, Order order) {
        Member memberId = memberRepository.findById(userId).get();
//        Order orders = orderRepository.findById(order.getOrderId()).get();

//        List<Order> orders = orderRepository.findAll();
//        if (memberId.equals(mem.getUsername())) {
            List<Order> orderList = orderRepository.findAllByMember_UserId(userId);
        return OrderDto.of(orderList);
        }

}
