package com.hc.product.mapper;

import com.hc.product.dto.ProductCreateDTO;
import com.hc.product.dto.ProductDTO;
import com.hc.product.dto.ProductUpdateDTO;
import com.hc.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = ProductMapperConfig.class)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product map(ProductCreateDTO createDTO);

    ProductDTO map(Product product);

    List<ProductDTO> map(List<Product> products);

    @Mapping(target = "id", ignore = true)
    void updateProductFromDto(ProductUpdateDTO updateDTO, @MappingTarget Product product);
}