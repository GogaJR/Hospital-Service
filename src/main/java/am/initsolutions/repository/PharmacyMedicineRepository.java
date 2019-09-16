package am.initsolutions.repository;

import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.models.joins.PharmacyMedicineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyMedicineRepository extends JpaRepository<PharmacyMedicine, PharmacyMedicineId> {
    List<PharmacyMedicine> getAllByPharmacyId(Long pharmacyId);
}
