package am.initsolutions.services;

import am.initsolutions.dto.OrderDto;
import am.initsolutions.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {
    @Override
    public List<OrderDto> getOrderDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            Long patientId = order.getPatient().getId();

            if (!orderDtos.isEmpty()) {
                for (int i=0; i<orderDtos.size(); i++) {
                    if (patientId == orderDtos.get(i).getClientId()) {
                        break;
                    } else if (i == orderDtos.size()-1) {
                        OrderDto orderDto = createOrderDto(orders, order, patientId);
                        orderDtos.add(orderDto);
                    }
                }
            } else {
                OrderDto orderDto = createOrderDto(orders, order, patientId);
                orderDtos.add(orderDto);
            }
        }

        return orderDtos;
    }

    private OrderDto createOrderDto(List<Order> orders, Order order, Long patientId) {
        List<String> medicineNames = new ArrayList<>();
        List<String> pharmacyNames = new ArrayList<>();
        List<String> pharmacyAddresses = new ArrayList<>();
        for (Order ord : orders) {
            if (ord.getPatient().getId() == patientId) {
                medicineNames.add(ord.getMedicine().getName());
                pharmacyNames.add(ord.getPharmacy().getName());
                pharmacyAddresses.add(ord.getPharmacy().getAddress());
            }
        }

        return OrderDto.from(order, medicineNames, pharmacyNames, pharmacyAddresses);
    }
}
