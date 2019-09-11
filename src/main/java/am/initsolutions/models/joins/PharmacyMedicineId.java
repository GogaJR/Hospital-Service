package am.initsolutions.models.joins;

<<<<<<< HEAD
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
=======
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PharmacyMedicineId {
>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
    private Long pharmacyId;

    private Long medicineId;
}
