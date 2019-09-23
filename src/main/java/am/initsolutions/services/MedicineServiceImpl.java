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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void update(Medicine medicine) {
        Medicine existedMedicine = medicineRepository.findOne(medicine.getId());
        existedMedicine.setName(medicine.getName());
        medicineRepository.save(existedMedicine);
    }

    @Override
    @Transactional
    public void delete(Long medicineId) {
        pharmacyMedicineRepository.deleteAllByMedicineId(medicineId);
        medicineRepository.delete(medicineId);
    }

    @Override
    public Medicine getOne(Long medicineId) {
        return medicineRepository.findOne(medicineId);
    }

    @Override
    public Medicine add(String name) {
        Medicine newMedicine = Medicine.builder()
                .name(name)
                .build();
        Medicine savedMedicine = medicineRepository.save(newMedicine);

        return savedMedicine;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Page<Medicine> getAll(Pageable pageable) {
        return  medicineRepository.findAll(pageable);
    }
}
