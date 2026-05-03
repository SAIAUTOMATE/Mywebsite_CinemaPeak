package com.cinemapeak.order.service;

import com.cinemapeak.order.dto.CreateOrderRequest;
import com.cinemapeak.order.exception.BadRequestException;
import com.cinemapeak.order.exception.OrderNotFoundException;
import com.cinemapeak.order.model.Order;
import com.cinemapeak.order.model.OrderStatus;
import com.cinemapeak.order.model.PurchaseType;
import com.cinemapeak.order.repository.InMemoryOrderRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final InMemoryOrderRepository repository;

  public OrderService(InMemoryOrderRepository repository) {
    this.repository = repository;
  }

  public Order createOrder(CreateOrderRequest request) {
    validateRequest(request);
    String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    boolean streamingEnabled = request.purchaseType() == PurchaseType.STREAMING_ACCESS;
    String downloadLink = request.purchaseType() == PurchaseType.DIGITAL_DOWNLOAD
        ? "https://download.cinemapeak.io/" + orderId
        : null;

    Order order = new Order(
        orderId,
        request.userId(),
        request.movieId(),
        request.purchaseType(),
        199,
        OrderStatus.CONFIRMED,
        downloadLink,
        streamingEnabled,
        isPhysical(request.purchaseType()) ? request.shippingAddress() : null,
        Instant.now());

    return repository.save(order);
  }

  public Order getOrder(String orderId) {
    return repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
  }

  public List<Order> getOrdersByUser(String userId) {
    return repository.findByUserId(userId);
  }

  private void validateRequest(CreateOrderRequest request) {
    if (isPhysical(request.purchaseType()) && request.shippingAddress() == null) {
      throw new BadRequestException("Shipping address required for physical orders");
    }
    if (!isPhysical(request.purchaseType()) && request.shippingAddress() != null) {
      throw new BadRequestException("Shipping address not allowed for digital/streaming orders");
    }
  }

  private boolean isPhysical(PurchaseType type) {
    return type == PurchaseType.PHYSICAL_CD || type == PurchaseType.PHYSICAL_DVD;
  }
}
