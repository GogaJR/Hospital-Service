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

    @ManyToOne
    @MapsId("medicineId")
    private Medicine medicine;
<<<<<<< HEAD

    @Column
    private int medicineCount;
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
}
