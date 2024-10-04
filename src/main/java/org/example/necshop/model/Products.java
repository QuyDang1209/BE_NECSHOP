package org.example.necshop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @OneToMany(mappedBy = "products")
    @JsonIgnoreProperties("products")
    private Set<Descriptions> descriptions;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "styles_id")
    private Styles style;

}
