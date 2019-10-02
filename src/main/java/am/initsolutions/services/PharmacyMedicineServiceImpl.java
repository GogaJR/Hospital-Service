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

import java.util.List;

@Service
public class PharmacyMedicineServiceImpl implements PharmacyMedicineService {

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineRepository medicineRepository;


    @Override
    public void delete(Long pharmacyId, Long medicineId) {
        pharmacyMedicineRepository.delete(new PharmacyMedicineId(pharmacyId, medicineId));
    }

    @Override
    public PharmacyMedicine addRelation(MedicineForm medicineForm) {
        Pharmacy pharmacy = pharmacyRepository.findOne(medicineForm.getPharmacyId());
        Medicine medicine = medicineRepository.findOne(medicineForm.getMedicineId());
        PharmacyMedicine newPharmacyMedicine = PharmacyMedicine.builder()
                .id(new PharmacyMedicineId(medicineForm.getPharmacyId(), medicineForm.getMedicineId()))
                .pharmacy(pharmacy)
                .medicine(medicine)
                .build();

        return pharmacyMedicineRepository.save(newPharmacyMedicine);
    }

    @Override
    public PharmacyMedicine get(Long pharmacyId, Long medicineId) {
        return pharmacyMedicineRepository.findOne(new PharmacyMedicineId(pharmacyId, medicineId));
    }

    @Override
    public List<PharmacyMedicine> getAllByPharmacyId(Long pharmacyId) {
        return pharmacyMedicineRepository.getAllByPharmacyId(pharmacyId);
    }

    @Override
    public List<PharmacyMedicine> getAllByMedicineId(Long medicineId) {
        return pharmacyMedicineRepository.getAllByMedicineId(medicineId);
    }
}
