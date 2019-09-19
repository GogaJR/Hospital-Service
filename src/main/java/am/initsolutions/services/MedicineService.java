package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;

import java.util.List;

public interface MedicineService {
    void update(MedicineFormWithId medicineForm);
    Medicine add(MedicineForm medicineForm);
    List<Medicine> getAll();
}
