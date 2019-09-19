package am.initsolutions.services;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Patient;
import am.initsolutions.models.User;

import java.util.List;

import java.util.List;

public interface PatientService {
    void setDoctor(Patient patient, Doctor doctor);
    Patient update(Patient patient);
    Patient get(Long id);
    Patient getByUserId(Long userId);
    Patient add(PatientForm patientForm);
    List<Doctor> getDoctors(Long id);
    List<Patient> patientListByDoctorId(Long doctorId);
}
