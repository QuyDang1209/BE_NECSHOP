package org.example.necshop.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.Category;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Styles;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqDTO {
    private Long id;
    private String name;
    private String code;
    private Set<DescriptionReqDTO> descriptions;
    private Set<Category> category;
    private Double price;
    private Styles style;
}
