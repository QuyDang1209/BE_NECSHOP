package org.example.necshop.service;
import org.example.necshop.model.Products;
import org.example.necshop.model.request.ProductReqDTO;
import org.example.necshop.model.response.ProductsResDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IProductService extends IGenericService<Products> {
    @Override
    Iterable<Products> findAll();

    @Override
    Optional<Products> findById(Long id);

    @Override
    Products save(Products products);

    @Override
    void remove(Long id);
    Products saveOrUpdate(ProductReqDTO productReqDTO);
    Iterable<ProductsResDTO> findAllProductsDTO();
    Page<ProductsResDTO> findAllProductsDTO(int page, int pageSize);
    Page<ProductsResDTO> findAllProductsDTOSortedByPrice(int page, int pageSize);
    Page<ProductsResDTO> findAllProductsDTOSortedByPriceAscending(int page, int pageSize);
    Page<ProductsResDTO> findAllFillterProductsDTO(int page, int pageSize, Double minPrice, Double maxPrice, String size,Integer color, Integer style,Integer category );
}
