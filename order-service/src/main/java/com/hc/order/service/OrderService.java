package com.hc.order.service;

import com.hc.order.dto.OrderCreateDTO;
import com.hc.order.dto.OrderUpdateDTO;
import com.hc.order.model.Order;

import java.util.List;

public interface OrderService {
    Order create(OrderCreateDTO createDTO);
    Order getById(Long id);
    List<Order> getAll(Long userId);
    Order update(Long id, OrderUpdateDTO updateDTO);
    void delete(Long id);
}