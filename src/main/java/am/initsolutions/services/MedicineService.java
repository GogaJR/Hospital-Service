package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;

public interface MedicineService {
    void update(MedicineFormWithId medicineForm);
    Medicine add(MedicineForm medicineForm);
}
