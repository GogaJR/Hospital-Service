package am.initsolutions.dto;

import am.initsolutions.models.Medicine;
import am.initsolutions.models.PharmacyMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyMedicineDto {
    private Long pharmacyId;
    private Medicine medicine;

    public static PharmacyMedicineDto from(PharmacyMedicine pharmacyMedicine) {
        return PharmacyMedicineDto.builder()
                .pharmacyId(pharmacyMedicine.getPharmacy().getId())
                .medicine(pharmacyMedicine.getMedicine())
                .build();
    }
}
