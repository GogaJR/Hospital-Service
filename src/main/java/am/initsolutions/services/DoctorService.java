package am.initsolutions.services;

import am.initsolutions.forms.DoctorForm;
import am.initsolutions.models.Doctor;



public interface DoctorService {
    Doctor add(DoctorForm doctorForm);

}
