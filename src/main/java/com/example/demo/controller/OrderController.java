package com.example.demo.controller;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @MutationMapping(name = "createOrder")
    public Order createOrders(@Argument String orderDetail, @Argument String address, @Argument int price, @Argument int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("user not found: " + userId);
        }
        Order order = new Order();
        order.setOrderDetail(orderDetail);
        order.setPrice(price);
        order.setAddress(address);
        order.setUser(user);

        Order order1 = orderService.createOrder(order);
        return order1;
    }

    @QueryMapping(name = "getOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @QueryMapping(name = "getOrder")
    public Order getOrder(@Argument int orderId) {
        return orderService.getOrderById(orderId);
    }

    @MutationMapping(name = "deleteOrder")
    public boolean deleteOrder(@Argument int orderId) {
        return orderService.deleteOrder(orderId);
    }
}
