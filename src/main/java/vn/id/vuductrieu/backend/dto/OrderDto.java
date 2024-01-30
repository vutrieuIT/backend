package vn.id.vuductrieu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.id.vuductrieu.backend.entity.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends Order {
    private List<OrderItemDto> orderItems;
}
