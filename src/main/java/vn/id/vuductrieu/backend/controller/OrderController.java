package vn.id.vuductrieu.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.vuductrieu.backend.dto.OrderDto;
import vn.id.vuductrieu.backend.entity.Order;
import vn.id.vuductrieu.backend.service.OrderService;
import vn.id.vuductrieu.backend.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{userId}")
    public List<Order> getAllOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/detail/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        try {
            orderService.createOrder(orderDto);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Order created successfully");
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        try {
            orderService.updateOrder(id, orderDto);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Order updated successfully");
        } catch (EmptyResultDataAccessException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Order deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }
}
