package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    private Date orderDate;

    private Long total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
