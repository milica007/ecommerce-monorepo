package com.hc.product.service;

import com.hc.product.dto.ProductSearchDTO;
import com.hc.product.repository.ProductRepository;
import com.hc.product.dto.ProductCreateDTO;
import com.hc.product.dto.ProductUpdateDTO;
import com.hc.product.exception.NotFoundException;
import com.hc.product.mapper.ProductMapper;
import com.hc.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    @Transactional
    public Product create(ProductCreateDTO createDTO) {
        var product = mapper.map(createDTO);
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found."));
    }

    @Override
    public List<Product> getAll(ProductSearchDTO searchDTO) {
        if (searchDTO == null) {
            return repository.findAll();
        }
        return repository.searchProducts(searchDTO);
    }

    @Override
    @Transactional
    public Product update(Long id, ProductUpdateDTO updateDTO) {
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
