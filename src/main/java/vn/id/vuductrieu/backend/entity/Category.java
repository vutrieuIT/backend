package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;

    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // not include this field in equals() and hashCode()
    @ToString.Exclude // not include this field in toString()
    private List<Product> products;
}
