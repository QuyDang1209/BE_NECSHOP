package org.example.necshop.service.impl;

import org.example.necshop.model.Color;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Products;
import org.example.necshop.model.response.DescriptionResDTO;
import org.example.necshop.repository.IColorRepository;
import org.example.necshop.repository.IDescriptionRepository;
import org.example.necshop.repository.IProductsRepository;
import org.example.necshop.service.IDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DescriptionService implements IDescriptionService {
    @Autowired
    private IDescriptionRepository descriptionRepository;
    @Autowired
    private IProductsRepository productsRepository;
    @Autowired
    private IColorRepository colorRepository;

    @Override
    public Iterable<Descriptions> findAll() {
        return null;
    }

    @Override
    public Optional<Descriptions> findById(Long id) {
        return descriptionRepository.findById(id);
    }


    @Override
    public Descriptions save(Descriptions descriptions) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Descriptions findByIdProductAndColorAndSize(Long productId, Long colorId, String size) {
        Products products = productsRepository.findById(productId).get();
        Color color = colorRepository.findById(colorId).get();
        return descriptionRepository.findByProductsAndSizeAndColor(products,size,color);
    }
}
