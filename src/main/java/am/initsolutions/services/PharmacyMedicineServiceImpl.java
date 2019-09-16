package am.initsolutions.services;

import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.models.joins.PharmacyMedicineId;
import am.initsolutions.repository.PharmacyMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyMedicineServiceImpl implements PharmacyMedicineService {

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;


    @Override
    public void delete(Long pharmacyId, Long medicineId) {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository.findOne(new PharmacyMedicineId(pharmacyId, medicineId));
        pharmacyMedicine.setMedicine(null);
        pharmacyMedicine.setPharmacy(null);
        PharmacyMedicine savedPharmacyMedicine = pharmacyMedicineRepository.save(pharmacyMedicine);

        pharmacyMedicineRepository.delete(savedPharmacyMedicine);
    }

    @Override
    public PharmacyMedicine get(Long pharmacyId, Long medicineId) {
        return pharmacyMedicineRepository.findOne(new PharmacyMedicineId(pharmacyId, medicineId));
    }

    @Override
    public List<PharmacyMedicine> getAllByPharmacyId(Long pharmacyId) {
        return pharmacyMedicineRepository.getAllByPharmacyId(pharmacyId);
    }
}
