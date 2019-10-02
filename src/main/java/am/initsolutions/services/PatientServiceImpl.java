package am.initsolutions.services;

import am.initsolutions.forms.OrderForm;
import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.*;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void setDoctor(Patient patient, Doctor doctor) {
        List<Doctor> doctors = patient.getDoctors();
        doctors.add(doctor);
        patient.setDoctors(doctors);

        patientRepository.save(patient);
    }

    @Override
    public void orderMedicine(Long patientId, OrderForm orderForm) {
        for (int i=0; i<orderForm.getMedicines().size(); i++) {
            Patient patient = patientRepository.findOne(patientId);
            Pharmacy pharmacy = pharmacyRepository.findByNameAndAddress(orderForm.getPharmacies().get(i), orderForm.getAddresses().get(i));
            Medicine medicine = medicineRepository.findByName(orderForm.getMedicines().get(i));

            Order order = Order.builder()
                    .date(Date.valueOf(LocalDate.now()))
                    .patient(patient)
                    .medicine(medicine)
                    .pharmacy(pharmacy)
                    .medicineCount(Integer.parseInt(orderForm.getCounts().get(i)))
                    .build();
            orderRepository.save(order);
        }
    }

    @Override
    public Patient update(Patient patient) {
        Patient existedPatient = patientRepository.findOne(patient.getId());
        existedPatient.setName(patient.getName());
        existedPatient.setSurname(patient.getSurname());
        existedPatient.setGender(patient.getGender());
        existedPatient.setAge(patient.getAge());
        existedPatient.setAddress(patient.getAddress());
        existedPatient.setPhoneNumber(patient.getPhoneNumber());

        return patientRepository.save(existedPatient);
    }

    @Override
    public Patient get(Long id) {
        return patientRepository.findOne(id);
    }

    @Override
    public Patient getByUserId(Long userId) {
        return patientRepository.findByUserId(userId);
    }

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
    public List<Doctor> getDoctors(Long id) {
        List<BigInteger> doctorsId = patientRepository.getDoctors(id);
        List<Doctor> doctors = new ArrayList<>();
        for (BigInteger doctorId : doctorsId) {
            Long docId = doctorId.longValue();
            doctors.add(doctorRepository.findOne(docId));
        }

        return doctors;
    }

    @Override
    public List<Patient> patientListByDoctorId(Long doctorId) {
        return patientRepository.patientListByDoctorId(doctorId);
    }
}
