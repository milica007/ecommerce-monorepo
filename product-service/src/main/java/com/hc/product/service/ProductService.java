package com.hc.product.service;

import com.hc.product.dto.ProductCreateDTO;
import com.hc.product.dto.ProductSearchDTO;
import com.hc.product.dto.ProductUpdateDTO;
import com.hc.product.model.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductCreateDTO createDTO);
    Product getById(Long id);
    List<Product> getAll(ProductSearchDTO searchDTO);
    Product update(Long id, ProductUpdateDTO updateDTO);
    void delete(Long id);
}
