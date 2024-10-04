package org.example.necshop.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="descriptions")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Descriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String size;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @OneToMany(mappedBy = "descriptions" )
    private Set<Images> img;
    @ManyToOne
    @JoinColumn(name = "products_id")
    @JsonIgnoreProperties("descriptions")
    private Products products;
}
