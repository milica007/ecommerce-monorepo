package com.hc.order.mapper;

import com.hc.order.dto.OrderDTO;
import com.hc.order.dto.OrderUpdateDTO;
import com.hc.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = OrderMapperConfig.class)
public interface OrderMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "user.username", source = "username")
    OrderDTO map(Order order);

    List<OrderDTO> map(List<Order> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateProductFromDto(OrderUpdateDTO updateDTO, @MappingTarget Order order);
}