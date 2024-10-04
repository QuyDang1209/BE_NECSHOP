package org.example.necshop.service;

import org.example.necshop.model.Descriptions;
import org.example.necshop.model.response.DescriptionResDTO;

import java.util.Optional;

public interface IDescriptionService extends IGenericService<Descriptions>{
    @Override
    Iterable<Descriptions> findAll();

    @Override
    Optional<Descriptions> findById(Long id);

    @Override
    Descriptions save(Descriptions descriptions);

    @Override
    void remove(Long id);
    Descriptions findByIdProductAndColorAndSize(Long productId, Long colorId, String size);
}
