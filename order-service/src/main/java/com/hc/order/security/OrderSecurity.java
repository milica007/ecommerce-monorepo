package com.hc.order.security;

import com.hc.order.model.Order;
import com.hc.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderSecurity {

    private final OrderRepository orderRepository;

    public boolean checkOwnership(Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Unauthorized access attempt to order ID: {}", orderId);
            return false;
        }

        String username = authentication.getName();
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            log.warn("Order ID {} not found.", orderId);
            return false;
        }

        boolean isOwner = order.getUsername().equals(username);
        if (!isOwner) {
            log.warn("User {} attempted to access order ID {} without permission.", username, orderId);
        }

        return isOwner;
    }
}
