package am.initsolutions.dto;

import am.initsolutions.models.PharmacyMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMedicineDto {
    private Long pharmacyId;
    private String pharmacyName;
    private int medicineCount;

    public static OrderMedicineDto from(PharmacyMedicine pharmacyMedicine) {
        return OrderMedicineDto.builder()
                .pharmacyId(pharmacyMedicine.getPharmacy().getId())
                .pharmacyName(pharmacyMedicine.getPharmacy().getName())
                .medicineCount(pharmacyMedicine.getMedicineCount())
                .build();
    }
}
