package am.initsolutions.services;

import am.initsolutions.forms.HospitalForm;
import am.initsolutions.models.Hospital;

public interface HospitalService {
    Hospital add(HospitalForm hospitalForm);
}
