package am.initsolutions.services;

import am.initsolutions.forms.PharmacyForm;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public void delete(Long id) {
        pharmacyRepository.delete(id);
    }

    @Override
    public void update(Pharmacy pharmacy) {
        Pharmacy existingPharmacy = pharmacyRepository.findOne(pharmacy.getId());
        existingPharmacy.setName(pharmacy.getName());
        existingPharmacy.setAddress(pharmacy.getAddress());
        existingPharmacy.setPhoneNumber(pharmacy.getPhoneNumber());
        existingPharmacy.setOpenAt(pharmacy.getOpenAt());
        existingPharmacy.setCloseAt(pharmacy.getCloseAt());

        pharmacyRepository.save(existingPharmacy);
    }

    @Override
    public Pharmacy getOne(Long id) {
        return pharmacyRepository.findOne(id);
    }

    @Override
    public Pharmacy add(PharmacyForm pharmacyForm) {
        Pharmacy newPharmacy = Pharmacy.builder()
                .name(pharmacyForm.getName())
                .address(pharmacyForm.getAddress())
                .phoneNumber(pharmacyForm.getPhoneNumber())
                .openAt(pharmacyForm.getOpenAt())
                .closeAt(pharmacyForm.getCloseAt())
                .build();

        return pharmacyRepository.save(newPharmacy);
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }
}
