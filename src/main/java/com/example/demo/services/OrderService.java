package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.helper.ExceptionHelper;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder (Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(ExceptionHelper :: throwResourceNotFoundException);
        return order;
    }

    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
        existingOrder.setOrderDetail(order.getOrderDetail());
        existingOrder.setOrderDetail(order.getAddress());
        existingOrder.setPrice(order.getPrice());
        return orderRepository.save(existingOrder);
    }

    public boolean deleteOrder(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(ExceptionHelper :: throwResourceNotFoundException);
        orderRepository.delete(order);
        return true;

    }
}
