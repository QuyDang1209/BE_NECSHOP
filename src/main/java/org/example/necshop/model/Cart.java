package org.example.necshop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CartItem> cartItems = new HashSet<>();
    private Double totalPrice;

    public void calculateTotalPrice() {
        this.totalPrice = this.cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
