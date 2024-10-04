package org.example.necshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.Products;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
    private Long productId;
    private String size;
    private Long colorId;
    private Integer quantity;
}
