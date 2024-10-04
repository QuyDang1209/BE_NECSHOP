package org.example.necshop.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.necshop.model.Category;
import org.example.necshop.model.Products;
import org.example.necshop.model.response.ProductsResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long> {
    @Query("SELECT NEW org.example.necshop.model.response.ProductsResDTO(p) from Products p")
    List<ProductsResDTO> findAllProductsDTO();

    @Query("SELECT p" +
            " from Products p  join Descriptions d on p.id = d.products.id\n" +
            "join Styles s on s.id = p.style.id\n" +
            "join Color c on c.id = d.color.id\n" +
            "join Images i on i.id = d.id\n" +
            "join fetch p.category ca\n" +
            "where (:minPrice IS NULL OR :maxPrice IS NULL OR p.price BETWEEN :minPrice AND :maxPrice)" +
            "and (:size is null or :size = d.size )" +
            "and (:color is null or :color = d.color.id) " +
            "and (:style is null or :style = p.style.id) " +
            "and (:category is null or :category = ca.id)"
    )
    Page<Products> fillterProducts(Pageable pageable,
                                         @Param("minPrice") Double minPrice,
                                         @Param("maxPrice") Double maxPrice,
                                         @Param("size") String size,
                                         @Param("color") Integer color,
                                         @Param("style") Integer style,
                                         @Param("category") Integer category);
}
