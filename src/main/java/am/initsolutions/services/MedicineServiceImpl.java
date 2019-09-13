package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.models.joins.PharmacyMedicineId;
import am.initsolutions.repository.MedicineRepository;
import am.initsolutions.repository.PharmacyMedicineRepository;
import am.initsolutions.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public Medicine add(MedicineForm medicineForm) {
        Medicine newMedicine = Medicine.builder()
                .name(medicineForm.getName())
                .build();
        Medicine savedMedicine = medicineRepository.save(newMedicine);

        Pharmacy existingPharmacy = pharmacyRepository.findOne(medicineForm.getPharmacyId());
        PharmacyMedicine pharmacyMedicine = PharmacyMedicine.builder()
                .id(new PharmacyMedicineId(existingPharmacy.getId(), savedMedicine.getId()))
                .medicine(savedMedicine)
                .pharmacy(existingPharmacy)
                .medicineCount(medicineForm.getMedicineCount())
                .build();
        pharmacyMedicineRepository.save(pharmacyMedicine);

        return savedMedicine;
    }
}
