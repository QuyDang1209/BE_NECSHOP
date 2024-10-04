package org.example.necshop.service.impl;

import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Images;
import org.example.necshop.model.Products;
import org.example.necshop.model.request.DescriptionReqDTO;
import org.example.necshop.model.request.ProductReqDTO;
import org.example.necshop.model.response.ProductsResDTO;
import org.example.necshop.repository.IDescriptionRepository;
import org.example.necshop.repository.IImageRepository;
import org.example.necshop.repository.IProductsRepository;
import org.example.necshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductsRepository productsRepository;
    @Autowired
    private IDescriptionRepository descriptionRepository;
    @Autowired
    private IImageRepository imageRepository;
    @Override
    public Iterable<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Iterable<ProductsResDTO> findAllProductsDTO() {
        return productsRepository.findAllProductsDTO();
    }

    @Override
    public Optional<Products> findById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public Products save(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public Products saveOrUpdate(ProductReqDTO productReqDTO) {
        if(productReqDTO.getId() != null){
            Products products = productsRepository.findById(productReqDTO.getId()).get();
            products.setName(productReqDTO.getName());
            products.setCode(productReqDTO.getCode());
            products.setPrice(productReqDTO.getPrice());
            products.setStyle(productReqDTO.getStyle());
            products.setCategory(productReqDTO.getCategory());
            save(products);
            Set<Descriptions> set = new HashSet<>();
            for(DescriptionReqDTO item: productReqDTO.getDescriptions()  ){
                Descriptions descriptions = new Descriptions();
                descriptions.setProducts(products);
                descriptions.setQuantity(item.getQuantity());
                descriptions.setSize(item.getSize());
                descriptions.setImg(item.getImg());
                descriptions.setColor(item.getColor());
                descriptionRepository.save(descriptions);
                for(Images images : item.getImg()){
                    Images images1 = new Images();
                    images1.setDescriptions(descriptions);
                    images1.setImg(images.getImg());
                    imageRepository.save(images1);
                }
                set.add(descriptions);
            }
            products.setDescriptions(set);
            save(products);
            return products;
        }
        else {
            Products products = new Products();
            products.setName(productReqDTO.getName());
            products.setCode(productReqDTO.getCode());
            products.setPrice(productReqDTO.getPrice());
            products.setStyle(productReqDTO.getStyle());
            products.setCategory(productReqDTO.getCategory());
            save(products);
            Set<Descriptions> set = new HashSet<>();
            for(DescriptionReqDTO item: productReqDTO.getDescriptions()  ){
                Descriptions descriptions = new Descriptions();
                descriptions.setProducts(products);
                descriptions.setQuantity(item.getQuantity());
                descriptions.setSize(item.getSize());
                descriptions.setImg(item.getImg());
                descriptions.setColor(item.getColor());
                descriptionRepository.save(descriptions);
                for(Images images : item.getImg()){
                    Images images1 = new Images();
                    images1.setDescriptions(descriptions);
                    images1.setImg(images.getImg());
                    imageRepository.save(images1);
                }
                set.add(descriptions);
            }
            products.setDescriptions(set);
            save(products);
            return products;
        }

    }

    @Override
    public void remove(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Page<ProductsResDTO> findAllProductsDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Products> productsPage =productsRepository.findAll(pageable);
        return productsPage.map(this ::convertoDTO);
    }
    @Override
    public Page<ProductsResDTO> findAllProductsDTOSortedByPrice(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize,Sort.by("price").descending());
        Page<Products> productsPage =productsRepository.findAll(pageable);
        return productsPage.map(this ::convertoDTO);
    }
    @Override
    public Page<ProductsResDTO> findAllProductsDTOSortedByPriceAscending(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize,Sort.by("price").ascending());
        Page<Products> productsPage =productsRepository.findAll(pageable);
        return productsPage.map(this ::convertoDTO);
    }

    @Override
    public Page<ProductsResDTO> findAllFillterProductsDTO(int page, int pageSize, Double minPrice, Double maxPrice, String size, Integer color, Integer style, Integer category) {
        Pageable pageable = PageRequest.of(page, pageSize,Sort.by("price").ascending());
        Page<Products> products = productsRepository.fillterProducts(pageable, minPrice, maxPrice, size, color,style,category);
        return products.map(this::convertoDTO);
    }

    private ProductsResDTO convertoDTO(Products products){
        ProductsResDTO productsRequestDTO = new ProductsResDTO();
        productsRequestDTO.setId(products.getId());
        productsRequestDTO.setCode(products.getCode());
        productsRequestDTO.setName(products.getName());
        productsRequestDTO.setCategory(products.getCategory());
        productsRequestDTO.setDescriptions(products.getDescriptions());
        productsRequestDTO.setPrice(products.getPrice());
        return productsRequestDTO;
    }
}
