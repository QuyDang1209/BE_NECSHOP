package org.example.necshop.repository;

import org.example.necshop.model.Color;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDescriptionRepository extends JpaRepository<Descriptions, Long> {
    Descriptions findByProductsAndSizeAndColor(Products products, String size, Color color);
}
