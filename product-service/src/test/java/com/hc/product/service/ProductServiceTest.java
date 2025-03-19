package com.hc.product.service;

import com.hc.product.dto.ProductSearchDTO;
import com.hc.product.mapper.ProductMapper;
import com.hc.product.model.Category;
import com.hc.product.repository.ProductRepository;
import com.hc.product.dto.ProductCreateDTO;
import com.hc.product.dto.ProductUpdateDTO;
import com.hc.product.exception.NotFoundException;
import com.hc.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProduct() {
        var createDTO = createProductCreateDTO().build();
        var product = createProduct().build();

        when(mapper.map(createDTO)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);

        var result = productService.create(createDTO);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(repository, times(1)).save(product);
    }

    @Test
    void shouldGetProductById() {
        var id = 1L;
        var product = createProduct().build();

        when(repository.findById(id)).thenReturn(Optional.of(product));

        var result = productService.getById(id);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldReturnAllProductsWhenSearchCriteriaIsEmpty() {
        var product1 = new Product(1L, "Test Product 1", "Description 1", Category.A);
        var product2 = new Product(2L, "Test Product 2", "Description 2", Category.B);
        List<Product> products = Arrays.asList(product1, product2);

        when(repository.findAll()).thenReturn(products);

        List<Product> result = productService.getAll(null);

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnFilteredProductsBasedOnSearchCriteria() {
        var product1 = new Product(1L, "Test Product 1", "Description 1", Category.A);
        List<Product> products = List.of(product1);

        var searchDTO = new ProductSearchDTO("Test Product 1", null, Category.A);

        when(repository.searchProducts(searchDTO)).thenReturn(products);

        List<Product> result = productService.getAll(searchDTO);

        assertEquals(1, result.size());
        assertEquals("Test Product 1", result.get(0).getName());
        verify(repository, times(1)).searchProducts(searchDTO);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProductNotFound() {
        var id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.getById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldUpdateProduct() {
        var id = 1L;
        var updateDTO = createProductUpdateDTO().build();
        var product = createProduct().build();

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        product.setName(updateDTO.name());
        product.setDescription(updateDTO.description());

        var result = productService.update(id, updateDTO);

        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        assertEquals("Updated Description", result.getDescription());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(product);
    }

    @Test
    void shouldDeleteProduct() {
        var id = 1L;
        var product = createProduct().build();

        when(repository.findById(id)).thenReturn(Optional.of(product));

        productService.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }

    private ProductCreateDTO.ProductCreateDTOBuilder createProductCreateDTO() {
        return ProductCreateDTO.builder()
                .name("Test Product")
                .description("Description");
    }

    private ProductUpdateDTO.ProductUpdateDTOBuilder createProductUpdateDTO() {
        return ProductUpdateDTO.builder()
                .name("Updated Product")
                .description("Updated Description");
    }

    private Product.ProductBuilder createProduct() {
        return Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Description");
    }
}
