package org.example.necshop.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionResDTO {
    private Long id;
    private Integer quantity;
    private String size;
    private Color color;
    private Set<Images> img;
    private Products products;

    public DescriptionResDTO(Descriptions descriptions){
        this.id = descriptions.getId();
        this.quantity = descriptions.getQuantity();
        this.size = descriptions.getSize();
        this.color = descriptions.getColor();
        this.img = descriptions.getImg();
        this.products = descriptions.getProducts();
    }

}
