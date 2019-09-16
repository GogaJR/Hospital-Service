package am.initsolutions.repository;

import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.models.joins.PharmacyMedicineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyMedicineRepository extends JpaRepository<PharmacyMedicine, PharmacyMedicineId> {
}
