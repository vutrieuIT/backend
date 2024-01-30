package vn.id.vuductrieu.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import vn.id.vuductrieu.backend.dto.OrderDto;
import vn.id.vuductrieu.backend.dto.OrderItemDto;
import vn.id.vuductrieu.backend.entity.Order;
import vn.id.vuductrieu.backend.entity.OrderItem;
import vn.id.vuductrieu.backend.repository.OrderItemRepository;
import vn.id.vuductrieu.backend.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new EmptyResultDataAccessException("order not found" ,1);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);

        List<OrderItemDto> orderItems = orderItemRepository.findByOrderId(id);
        if (orderItems == null) {
            throw new EmptyResultDataAccessException("order items not found" ,1);
        }
        orderDto.setOrderItems(orderItemRepository.findByOrderId(id));

        return orderDto;
    }

    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        order = orderRepository.save(order);

        Long totalPrice = 0L;

        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            totalPrice += orderItemDto.getPrice() * orderItemDto.getQuantity();
            BeanUtils.copyProperties(orderItemDto, orderItem);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }

        order.setTotal(totalPrice);
        orderRepository.save(order);
    }

    public void updateOrder(Long orderId, OrderDto orderDto) {
        if (orderDto.getOrderItems() == null) {
            throw new EmptyResultDataAccessException("need least 1 product" ,1);
        }

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new EmptyResultDataAccessException("order not found" ,1);
        }

        BeanUtils.copyProperties(orderDto, order);
        orderRepository.save(order);

        Long totalPrice = 0L;

        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId()).orElse(null);
            if (orderItem == null) {
                throw new EmptyResultDataAccessException("order item not found" ,1);
            }
            totalPrice += orderItemDto.getPrice() * orderItemDto.getQuantity();
            BeanUtils.copyProperties(orderItemDto, orderItem);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new EmptyResultDataAccessException("order not found" ,1);
        }
        orderRepository.delete(order);
    }
}
