package com.cinemapeak.order.controller;

import com.cinemapeak.order.dto.CreateOrderRequest;
import com.cinemapeak.order.dto.CreateOrderResponse;
import com.cinemapeak.order.model.Order;
import com.cinemapeak.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/orders")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
    Order order = orderService.createOrder(request);
    return new CreateOrderResponse(order.orderId(), order.status().name(), "Order placed successfully");
  }

  @GetMapping("/orders/{orderId}")
  public Order getOrderById(@PathVariable String orderId) {
    return orderService.getOrder(orderId);
  }

  @GetMapping("/users/{userId}/orders")
  public List<Map<String, String>> getOrdersByUser(@PathVariable String userId) {
    return orderService.getOrdersByUser(userId).stream()
        .map(order -> Map.of(
            "orderId", order.orderId(),
            "movieId", order.movieId(),
            "purchaseType", order.purchaseType().name(),
            "status", order.status().name()))
        .toList();
  }
}
