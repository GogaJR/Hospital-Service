package am.initsolutions.services;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Patient;

public interface PatientService {
    Patient add(PatientForm patientForm);
}
