package org.example.necshop.model.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.Category;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Products;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResDTO {
    private Long id;
    private String name;
    private String code;
//    @OneToMany(mappedBy = "products")
    private Set<Descriptions> descriptions;
//    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category;
    private Double price;
    public ProductsResDTO(Products products){
        this.id = products.getId();
        this.name = products.getName();
        this.code = products.getCode();
        this.descriptions = products.getDescriptions();
        this.category = products.getCategory();
        this.price = products.getPrice();
    }
}
