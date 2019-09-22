package am.initsolutions.services;

import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicineService {
    void update(MedicineFormWithId medicineForm);
    void update(Medicine medicine);
    void delete(Long medicineId);
    Medicine getOne(Long medicineId);
    Medicine add(String name);
    List<Medicine> getAll();
    Page<Medicine> getAll(Pageable pageable);
}
