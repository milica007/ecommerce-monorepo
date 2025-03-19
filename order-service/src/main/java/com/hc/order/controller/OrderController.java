package com.hc.order.controller;

import com.hc.order.dto.OrderCreateDTO;
import com.hc.order.dto.OrderDTO;
import com.hc.order.dto.OrderUpdateDTO;
import com.hc.order.mapper.OrderMapper;
import com.hc.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class OrderController implements OrderApi {

    private final OrderService service;
    private final OrderMapper mapper;

    @Override
    public OrderDTO create(OrderCreateDTO createDTO) {
        return mapper.map(service.create(createDTO));
    }

    @Override
    public OrderDTO getById(Long id) {
        return mapper.map(service.getById(id));
    }

    @Override
    public List<OrderDTO> getAll(Long userId) {
        return mapper.map(service.getAll(userId));
    }

    @Override
    public OrderDTO update(Long id, OrderUpdateDTO updateDTO) {
        return mapper.map(service.update(id, updateDTO));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
