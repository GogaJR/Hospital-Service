package am.initsolutions.models.joins;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PharmacyMedicineId {
    private Long pharmacyId;

    private Long medicineId;
}
