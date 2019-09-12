package am.initsolutions.models.joins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PharmacyMedicineId implements Serializable {
    private Long pharmacyId;

    private Long medicineId;
}
