package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.models.joins.PharmacyMedicineId;
import am.initsolutions.repository.MedicineRepository;
import am.initsolutions.repository.PharmacyMedicineRepository;
import am.initsolutions.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacyMedicineService pharmacyMedicineService;

    @Override
    public void update(MedicineFormWithId medicineForm) {
        Medicine medicineCandidate = medicineRepository.findByName(medicineForm.getName());
        Pharmacy existedPharmacy = pharmacyRepository.findOne(medicineForm.getPharmacyId());
        pharmacyMedicineService.delete(medicineForm.getPharmacyId(), medicineForm.getId());
        if (medicineCandidate == null) {
            Medicine newMedicine = Medicine.builder()
                    .name(medicineForm.getName())
                    .build();
            Medicine savedMedicine = medicineRepository.save(newMedicine);

            PharmacyMedicine newPharmacyMedicine = PharmacyMedicine.builder()
                    .id(new PharmacyMedicineId(medicineForm.getPharmacyId(), savedMedicine.getId()))
                    .medicine(savedMedicine)
                    .pharmacy(existedPharmacy)
                    .medicineCount(medicineForm.getCount())
                    .build();
            pharmacyMedicineRepository.save(newPharmacyMedicine);

            return;
        }

        PharmacyMedicine newPharmacyMedicine = PharmacyMedicine.builder()
                .id(new PharmacyMedicineId(medicineForm.getPharmacyId(), medicineCandidate.getId()))
                .medicine(medicineCandidate)
                .pharmacy(existedPharmacy)
                .medicineCount(medicineForm.getCount())
                .build();
        pharmacyMedicineRepository.save(newPharmacyMedicine);
    }

    @Override
    public Medicine add(MedicineForm medicineForm) {
        Medicine medicineCandidate = medicineRepository.findByName(medicineForm.getName());
        Pharmacy existingPharmacy = pharmacyRepository.findOne(medicineForm.getPharmacyId());
        if (medicineCandidate == null) {
            Medicine newMedicine = Medicine.builder()
                    .name(medicineForm.getName())
                    .build();
            Medicine savedMedicine = medicineRepository.save(newMedicine);

            PharmacyMedicine pharmacyMedicine = PharmacyMedicine.builder()
                    .id(new PharmacyMedicineId(existingPharmacy.getId(), savedMedicine.getId()))
                    .medicine(savedMedicine)
                    .pharmacy(existingPharmacy)
                    .medicineCount(medicineForm.getMedicineCount())
                    .build();
            pharmacyMedicineRepository.save(pharmacyMedicine);

            return savedMedicine;
        }

        PharmacyMedicine pharmacyMedicine = PharmacyMedicine.builder()
                .id(new PharmacyMedicineId(existingPharmacy.getId(), medicineCandidate.getId()))
                .pharmacy(existingPharmacy)
                .medicine(medicineCandidate)
                .medicineCount(medicineForm.getMedicineCount())
                .build();
        pharmacyMedicineRepository.save(pharmacyMedicine);

        return medicineCandidate;
    }
}
