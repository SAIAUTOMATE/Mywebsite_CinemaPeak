package com.cinemapeak.order.repository;

import com.cinemapeak.order.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOrderRepository {
  private final Map<String, Order> orders = new ConcurrentHashMap<>();

  public Order save(Order order) {
    orders.put(order.orderId(), order);
    return order;
  }

  public Optional<Order> findById(String orderId) {
    return Optional.ofNullable(orders.get(orderId));
  }

  public List<Order> findByUserId(String userId) {
    List<Order> result = new ArrayList<>();
    orders.values().forEach(order -> { if (order.userId().equals(userId)) result.add(order); });
    return result;
  }
}
