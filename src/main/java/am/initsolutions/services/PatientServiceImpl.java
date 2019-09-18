package am.initsolutions.services;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Patient;
import am.initsolutions.models.User;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.repository.PatientRepository;
import am.initsolutions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Patient add(PatientForm patientForm) {
        User newUser = User.builder()
                .userType(UserType.PATIENT)
                .email(patientForm.getMail())
                .hashPassword(passwordEncoder.encode(patientForm.getPassword()))
                .build();
        User savedUser = userRepository.save(newUser);

        Patient newPatient = Patient.builder()
                .name(patientForm.getName())
                .surname(patientForm.getSurname())
                .age(patientForm.getAge())
                .gender(patientForm.getGender())
                .address(patientForm.getAddress())
                .phoneNumber(patientForm.getPhoneNumber())
                .user(savedUser)
                .build();

           return patientRepository.save(newPatient);
    }

    @Override
    public List<Patient> patientListByDoctorId(Long doctorId) {
        return patientRepository.patientListByDoctorId(doctorId);
    }
}
