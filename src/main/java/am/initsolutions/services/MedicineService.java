package am.initsolutions.services;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.models.Medicine;

public interface MedicineService {
    Medicine add(MedicineForm medicineForm);
}
