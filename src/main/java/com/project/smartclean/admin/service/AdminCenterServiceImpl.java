package com.project.smartclean.admin.service;

import com.project.smartclean.order.repository.OrderRepository;
import com.project.smartclean.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCenterServiceImpl {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


}
