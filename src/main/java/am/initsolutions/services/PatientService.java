package am.initsolutions.services;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Patient;

import java.util.List;

public interface PatientService {
    Patient add(PatientForm patientForm);
    List<Patient> patientListByDoctorId(Long doctorId);
}
