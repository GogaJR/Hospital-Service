package am.initsolutions.controller;

import am.initsolutions.dto.PharmacyMedicineDto;
import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.services.MedicineService;
import am.initsolutions.services.PharmacyMedicineService;
import am.initsolutions.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PharmacyAdminController {
    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyMedicineService pharmacyMedicineService;

    @GetMapping("/pharmacyAdmin")
    public String getPharmacyPage(ModelMap modelMap) {
        List<Pharmacy> pharmacies = pharmacyService.getAll();
        modelMap.addAttribute("pharmacies", pharmacies);

        return "pharmacyAdmin";
    }

    @GetMapping("/pharmacyAdmin/addMedicine/{id}")
    public String getAddMedicinePage(@PathVariable("id") Long id, ModelMap modelMap,
                                     @ModelAttribute("added") Boolean added) {
        List<Medicine> medicines = medicineService.getAll();
        modelMap.addAttribute("medicineList", medicines);
        modelMap.addAttribute("pharmacyId", id);

        if (added != null) {
            modelMap.addAttribute("added", added);
        }

        return "addMedicineToPharmacy";
    }

    @PostMapping("/pharmacyAdmin/addMedicine")
    public String addMedicine(MedicineForm medicineForm, ModelMap modelMap, HttpServletRequest request) {
        PharmacyMedicine savedPharmacyMedicine = pharmacyMedicineService.addRelation(medicineForm);

        if (savedPharmacyMedicine != null) {
            request.getSession().setAttribute("added", true);
            return "redirect:/pharmacyAdmin/addMedicine/" + medicineForm.getPharmacyId();
        }

        modelMap.addAttribute("error", true);
        return "redirect:/pharmacyAdmin/addMedicine/" + medicineForm.getPharmacyId();
    }

    @GetMapping("/pharmacyAdmin/medicines/{id}")
    public String listMedicine(@PathVariable("id") Long id, ModelMap modelMap) {
        List<PharmacyMedicine> pharmacyMedicines = pharmacyMedicineService.getAllByPharmacyId(id);
        List<PharmacyMedicineDto> pharmacyMedicineDtos = new ArrayList<>();

        for (PharmacyMedicine pharmacyMedicine : pharmacyMedicines) {
            pharmacyMedicineDtos.add(PharmacyMedicineDto.from(pharmacyMedicine));
        }
        modelMap.addAttribute("pharmacyMedicines", pharmacyMedicineDtos);

        return "medicineList";
    }

    @GetMapping("/pharmacyAdmin/medicines/{pharmacyId}/delete/{medicineId}")
    public String deleteMedicine(@PathVariable("pharmacyId") Long pharmacyId,
                                 @PathVariable("medicineId") Long medicineId) {
        pharmacyMedicineService.delete(pharmacyId, medicineId);

        return "redirect:/pharmacyAdmin/medicines/" + pharmacyId;
    }

//    @GetMapping("/pharmacyAdmin/medicines/{pharmacyId}/edit/{medicineId}")
//    public String getEditMedicinePage(@PathVariable("pharmacyId") Long pharmacyId,
//                                      @PathVariable("medicineId") Long medicineId, ModelMap modelMap) {
//        PharmacyMedicine pharmacyMedicine = pharmacyMedicineService.get(pharmacyId, medicineId);
//        PharmacyMedicineDto pharmacyMedicineDto = PharmacyMedicineDto.from(pharmacyMedicine);
//        modelMap.addAttribute("pharmacyMedicine", pharmacyMedicineDto);
//
//        //TODO
//
//        return "editMedicine";
//    }
//
//    @PostMapping("/pharmacyAdmin/medicines/{pharmacyId}/edit")
//    public String editMedicine(@PathVariable("pharmacyId") Long pharmacyId, MedicineFormWithId medicineForm) {
//        medicineService.update(medicineForm);
//
//        return "redirect:/pharmacyAdmin/medicines/" + pharmacyId;
//    }
}
