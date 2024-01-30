package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long productId;

    private Long quantity;
}
