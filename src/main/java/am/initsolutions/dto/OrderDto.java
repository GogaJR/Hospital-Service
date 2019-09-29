package am.initsolutions.dto;

import am.initsolutions.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private Long clientId;
    private String clientName;
    private String clientSurname;
    private String clientPhoneNumber;
    private List<String> orderedMedicineNames;
    private List<String> pharmacyNamesOfOrderedMedicines;
    private List<String> pharmacyAddresses;
    private LocalDate orderDate;

    public static OrderDto from(Order order, List<String> medicineNames, List<String> pharmacyNames, List<String> pharmacyAddresses) {
        return OrderDto.builder()
                .orderId(order.getId())
                .clientId(order.getPatient().getId())
                .clientName(order.getPatient().getName())
                .clientSurname(order.getPatient().getSurname())
                .clientPhoneNumber(order.getPatient().getPhoneNumber())
                .orderedMedicineNames(medicineNames)
                .pharmacyNamesOfOrderedMedicines(pharmacyNames)
                .pharmacyAddresses(pharmacyAddresses)
                .orderDate(order.getDate().toLocalDate())
                .build();
    }
}
