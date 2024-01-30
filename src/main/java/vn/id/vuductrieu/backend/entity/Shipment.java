package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shipment {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    private Long orderId;

    private Long userId;

    private String shipmentMethod;

    private Long amount;

    private String status;
}
