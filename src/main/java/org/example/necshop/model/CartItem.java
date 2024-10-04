package org.example.necshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.necshop.model.response.DescriptionResDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name ="descriptions_id")
    private Descriptions descriptions;
    private Integer quantity;
    private Double totalPrice;


    public void calculateTotalPrice() {
        this.totalPrice = this.quantity * this.getDescriptions().getProducts().getPrice();
    }

}
