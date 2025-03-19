package com.hc.product.controller;

import com.hc.product.dto.ProductDTO;
import com.hc.product.dto.ProductSearchDTO;
import com.hc.product.mapper.ProductMapper;
import com.hc.product.service.ProductService;
import com.hc.product.controller.definition.ProductApi;
import com.hc.product.dto.ProductCreateDTO;
import com.hc.product.dto.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class ProductController implements ProductApi {

    private final ProductService service;
    private final ProductMapper mapper;

    @Override
    public ProductDTO create(ProductCreateDTO createDTO) {
        return mapper.map(service.create(createDTO));
    }

    @Override
    public ProductDTO getById(Long id) {
        return mapper.map(service.getById(id));
    }

    @Override
    public List<ProductDTO> getAll(ProductSearchDTO searchDTO) {
        return mapper.map(service.getAll(searchDTO));
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO updateDTO) {
        return mapper.map(service.update(id, updateDTO));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
