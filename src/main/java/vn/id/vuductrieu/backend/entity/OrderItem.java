package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @OneToOne
    private Product product;

    @OneToOne
    private Order order;

    private Long quantity;

    private Long price;
}
