package am.initsolutions.models;

import am.initsolutions.models.joins.PharmacyMedicineId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="pharmacy_medicine")
public class PharmacyMedicine {
    @EmbeddedId
    private PharmacyMedicineId id;

    @ManyToOne
    @MapsId("pharmacyId")
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("medicineId")
    private Medicine medicine;

    @Column
    private int medicineCount;
}
