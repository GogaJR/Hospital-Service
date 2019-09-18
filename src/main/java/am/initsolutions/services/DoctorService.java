package am.initsolutions.services;

import am.initsolutions.forms.DoctorForm;
import am.initsolutions.models.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor add(DoctorForm doctorForm);
    List<Doctor> getAll();
    Doctor get(Long id);
    void deleteDoctor(Long id);
    Doctor getByUserId(long id);


}
