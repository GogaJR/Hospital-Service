package am.initsolutions.controller;

import am.initsolutions.dto.PharmacyMedicineDto;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.services.MedicineService;
import am.initsolutions.services.PharmacyMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MedicineController {
    @Autowired
    private PharmacyMedicineService pharmacyMedicineService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/pharmacyAdmin/medicines/{pharmacyId}/delete/{medicineId}")
    public String deleteMedicine(@PathVariable("pharmacyId") Long pharmacyId,
                                 @PathVariable("medicineId") Long medicineId) {
        pharmacyMedicineService.delete(pharmacyId, medicineId);

        return "redirect:/pharmacyAdmin/medicines/" + pharmacyId;
    }

    @GetMapping("/pharmacyAdmin/medicines/{pharmacyId}/edit/{medicineId}")
    public String getEditMedicinePage(@PathVariable("pharmacyId") Long pharmacyId,
                               @PathVariable("medicineId") Long medicineId, ModelMap modelMap) {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineService.get(pharmacyId, medicineId);
        PharmacyMedicineDto pharmacyMedicineDto = PharmacyMedicineDto.from(pharmacyMedicine);
        modelMap.addAttribute("pharmacyMedicine", pharmacyMedicineDto);

        return "editMedicine";
    }

    @PostMapping("/pharmacyAdmin/medicines/{pharmacyId}/edit")
    public String editMedicine(@PathVariable("pharmacyId") Long pharmacyId, MedicineFormWithId medicineForm) {
        medicineService.update(medicineForm);

        return "redirect:/pharmacyAdmin/medicines/" + pharmacyId;
    }
}
