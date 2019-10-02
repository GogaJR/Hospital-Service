package am.initsolutions.services;

import am.initsolutions.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    void deleteOrder(Long id);
}
