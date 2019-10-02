package am.initsolutions.services;

import am.initsolutions.forms.HospitalForm;
import am.initsolutions.models.Hospital;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

//    @Autowired
//    private DoctorRepository doctorRepository;

    @Override
//    @Transactional
    public void delete(Long id) {
//        doctorRepository.deleteByHospitalId(id);
        hospitalRepository.delete(id);
    }

    @Override
    public void update(Hospital hospital) {
        Hospital existingHospital = hospitalRepository.findOne(hospital.getId());
        existingHospital.setName(hospital.getName());
        existingHospital.setAddress(hospital.getAddress());
        existingHospital.setExecutive(hospital.getExecutive());
        existingHospital.setPhoneNumber(hospital.getPhoneNumber());
        existingHospital.setSite(hospital.getSite());

        hospitalRepository.save(existingHospital);
    }

    @Override
    public Hospital get(Long id) {
        return hospitalRepository.findOne(id);
    }

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

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }
}
