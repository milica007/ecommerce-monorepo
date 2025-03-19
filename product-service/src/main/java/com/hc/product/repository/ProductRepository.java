package com.hc.product.repository;

import com.hc.product.dto.ProductSearchDTO;
import com.hc.product.model.Category;
import com.hc.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @NonNull
    @Override
    List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE " +
            "(:#{#searchDTO.name} IS NULL OR p.name LIKE %:#{#searchDTO.name}%) AND " +
            "(:#{#searchDTO.description} IS NULL OR p.description LIKE %:#{#searchDTO.description}%) AND " +
            "(:#{#searchDTO.category} IS NULL OR p.category = :#{#searchDTO.category}) AND " +
            "(COALESCE(:#{#searchDTO.ids}, NULL) IS NULL OR p.id IN :#{#searchDTO.ids})")
    List<Product> searchProducts(@Param("searchDTO") ProductSearchDTO searchDTO);
}
