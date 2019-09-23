package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.models.PharmacyMedicine;

import java.util.List;

public interface PharmacyMedicineService {
    void delete(Long pharmacyId, Long medicineId);
    PharmacyMedicine addRelation(MedicineForm medicineForm);
    PharmacyMedicine get(Long pharmacyId, Long medicineId);
    List<PharmacyMedicine> getAllByPharmacyId(Long pharmacyId);
    List<PharmacyMedicine> getAllByMedicineId(Long medicineId);
}
