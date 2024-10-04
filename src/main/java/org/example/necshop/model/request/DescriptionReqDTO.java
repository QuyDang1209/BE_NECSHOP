package org.example.necshop.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.Color;
import org.example.necshop.model.Images;
import org.example.necshop.model.Products;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionReqDTO {
    private Long id;
    private Integer quantity;
    private String size;
    private Color color;
    private Set<Images> img;
    private Products products;
}
