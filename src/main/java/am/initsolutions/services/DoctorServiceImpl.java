package am.initsolutions.services;

import am.initsolutions.forms.DoctorForm;
import am.initsolutions.models.Department;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Hospital;
import am.initsolutions.models.User;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.HospitalRepository;
import am.initsolutions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Transactional
    public Doctor add(DoctorForm doctorForm) {
        User newUser = User.builder()
                .userType(UserType.PATIENT)
                .email(doctorForm.getMail())
                .hashPassword(passwordEncoder.encode(doctorForm.getPassword()))
                .build();
        User savedUser = userRepository.save(newUser);

        Hospital hospital = hospitalRepository.findOne(doctorForm.getHospitalId());
        Department department=departmentRepository.findOne(doctorForm.getDepartmentId());
        Doctor newDoctor = Doctor.builder()
                .name(doctorForm.getName())
                .surname(doctorForm.getSurname())
                .age(doctorForm.getAge())
                .gender(doctorForm.getGender())
                .phoneNumber(doctorForm.getPhoneNumber())
                .hospital(hospital)
                .department(department)
                .user(savedUser)
                .build();

        return doctorRepository.save(newDoctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor get(Long id) {
        return doctorRepository.findOne(id);
    }

    @Override
    public void deleteDoctor(Long id) {
             doctorRepository.delete(id);
    }


}
