package am.initsolutions.services;

import am.initsolutions.forms.HospitalForm;
import am.initsolutions.models.Hospital;
import am.initsolutions.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Hospital add(HospitalForm hospitalForm) {
        Hospital newHospital = Hospital.builder()
                .name(hospitalForm.getName())
                .executive(hospitalForm.getExecutive())
                .address(hospitalForm.getAddress())
                .phoneNumber(hospitalForm.getPhoneNumber())
                .site(hospitalForm.getSite())
                .build();

        return hospitalRepository.save(newHospital);
    }
}
