package am.initsolutions.services;

import am.initsolutions.forms.PharmacyForm;
import am.initsolutions.models.Pharmacy;

import java.util.List;

public interface PharmacyService {
    void delete(Long id);
    void update(Pharmacy pharmacy);
    Pharmacy getOne(Long id);
    Pharmacy add(PharmacyForm pharmacyForm);
    List<Pharmacy> getAll();
}
