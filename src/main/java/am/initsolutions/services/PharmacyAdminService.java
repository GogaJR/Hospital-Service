package am.initsolutions.services;

import am.initsolutions.dto.OrderDto;
import am.initsolutions.models.Order;

import java.util.List;

public interface PharmacyAdminService {
    List<OrderDto> getOrderDtos(List<Order> orders);
}
