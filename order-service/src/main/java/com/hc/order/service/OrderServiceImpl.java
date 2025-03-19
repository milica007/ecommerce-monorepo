package com.hc.order.service;

import com.hc.order.client.ProductServiceClient;
import com.hc.order.dto.OrderCreateDTO;
import com.hc.order.dto.OrderUpdateDTO;
import com.hc.order.dto.ProductDTO;
import com.hc.order.exception.NotFoundException;
import com.hc.order.mapper.OrderMapper;
import com.hc.order.model.Order;
import com.hc.order.repository.OrderRepository;
import com.hc.order.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ProductServiceClient productServiceClient;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Override
    @Transactional
    public Order create(OrderCreateDTO createDTO) {
        List<ProductDTO> products = productServiceClient.getProductsByIds(
                createDTO.productIds(),
                authenticatedUserProvider.getCurrentToken()
        );

        boolean allAvailable = products.stream().allMatch(product -> product.stock() > 0);
        if (!allAvailable) {
            throw new RuntimeException("One or more products are out of stock.");
        }

        Order order = new Order();
        order.setProductIds(createDTO.productIds());
        order.setUserId(authenticatedUserProvider.getUserId());
        order.setUsername(authenticatedUserProvider.getUsername());

        return repository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found."));
    }

    @Override
    public List<Order> getAll(Long userId) {
        if (userId != null) {
            return repository.findByUserId(userId);
        }

        return repository.findAll();
    }

    @Override
    @Transactional
    public Order update(Long id, OrderUpdateDTO updateDTO) {
        var product = getById(id);
        mapper.updateProductFromDto(updateDTO, product);
        return repository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }
}
