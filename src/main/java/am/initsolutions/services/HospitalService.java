package am.initsolutions.services;

import am.initsolutions.forms.HospitalForm;
import am.initsolutions.models.Hospital;

public interface HospitalService {
    void delete(Long id);
    void update(Hospital hospital);
    Hospital get(Long id);
    Hospital add(HospitalForm hospitalForm);
}
