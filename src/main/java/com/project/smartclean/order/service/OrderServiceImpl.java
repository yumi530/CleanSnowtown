package com.project.smartclean.order.service;

import com.project.smartclean.common.utils.CommonUtils;
import com.project.smartclean.order.dto.UserOrderDto;
import com.project.smartclean.order.dto.WasteDto;
import com.project.smartclean.order.entity.Item;
import com.project.smartclean.order.entity.UserOrder;
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

//    @Override
//    public UserOrder wasteOrder(OrderForm parameter) {
//        String orderNumber = CommonUtils.getOrderUUID();
//
//        UserOrder userOrder = UserOrder.builder()
//                .member(userId)
//               //.orderUserId(parameter.getUserOrderId())
//                .orderUserName(parameter.getOrderUserName())
//                .orderUserPhone(parameter.getOrderUserPhone())
//                .address1(parameter.getAddress1())
//                .address2(parameter.getAddress2())
//                .item(getItemName())
//
//                .orderNumber(orderNumber)
//
//                .disposeDate(parameter.getDisposeDate())
//                .orderDate(LocalDateTime.now())
//                .districtName(parameter.getDistrictName())
//                .build();
//       orderRepository.save(userOrder);
//
//        return userOrder;
//    }


    @Override
    public UserOrder order(OrderForm parameter) {
        String orderId = CommonUtils.getOrderUUID();

        UserOrder userOrder = UserOrder.builder()
                .orderId(orderId)
                .member(parameter.getUserId())
                .orderUserName(parameter.getOrderUserName())
                .orderUserPhone(parameter.getOrderUserPhone())
                .address1(parameter.getAddress1())
                .address2(parameter.getAddress2())
                .disposeDate(parameter.getDisposeDate())
                .orderDate(LocalDateTime.now())
                .districtName(parameter.getDistrictName())
                .build();

        orderRepository.save(userOrder);

        return userOrder;
    }

//    @Override
//    public Order order(OrderForm parameter) {
//        String orderNumber = CommonUtils.getOrderUUID();
//
//        Order Order = Order.builder()
//                .member(parameter.getUserId())
//                .orderUserName(parameter.getOrderUserName())
//                .orderUserPhone(parameter.getOrderUserPhone())
//                .address1(parameter.getAddress1())
//                .address2(parameter.getAddress2())
//                .item((List<Item>) parameter.getItemName())
//                //.items(new ArrayList<>())
//                //.items(List.of(items))
////                for(Item item : items) {
////                    parameter.setItemName(item);
////                }
//
//                //.itemDetail(parameter.getItemDetail())
//
//                .orderNumber(orderNumber)
//
//                .disposeDate(parameter.getDisposeDate())
//                .orderDate(LocalDateTime.now())
//                .districtName(parameter.getDistrictName())
//                .build();
//
//        orderRepository.save(userOrder);
//
//        return userOrder;
//    }


    @Override
    public List<WasteDto> wasteFrontList(Item item) {
        //MemberDto parameter = new MemberDto();
        //long totalCount = memberMapper.selectListCount(parameter);


        List<Item> list = itemRepository.findAll();


//        if (!CollectionUtils.isEmpty(list)) {
//            int i = 0;
//            for (Item x : list) {
////                x.setTotalCount(totalCount);
////                x.setSeq(totalCount - parameter.getPageStart() - i);
//                i++;
//            }
//        }
        return WasteDto.of(list);
    }


    @Override
    public List<UserOrder> wastesOrder(OrderForm parameter) {
        return orderRepository.findAll();
    }


    @Override
    public UserOrderDto userOrderDetail(String orderId) {
        Optional<UserOrder> optionalUserOrder = orderRepository.findById(orderId);
        if (!optionalUserOrder.isPresent()) {
            return null;
        }
        UserOrder detail = optionalUserOrder.get();
        return UserOrderDto.of(detail);
    }

    @Override
    public boolean pickupStatus(String orderNumber, String pickupStatus) {
        Optional<UserOrder> optionalUserOrder = orderRepository.findById(orderNumber);
        if (!optionalUserOrder.isPresent()) {
            throw new RuntimeException("주문 정보가 존재하지 않습니다.");
        }
        UserOrder userOrder = optionalUserOrder.get();
        userOrder.setPickupStatus(pickupStatus);
        orderRepository.save(userOrder);
        return true;
    }

    @Override
    public UserOrderDto detail(String userId) {
        Optional<UserOrder> optionalUserOrder = orderRepository.findById(userId);
        if (!optionalUserOrder.isPresent()) {
            return null;
        }
        UserOrder userOrder = optionalUserOrder.get();

        return UserOrderDto.of(userOrder);
    }
}
