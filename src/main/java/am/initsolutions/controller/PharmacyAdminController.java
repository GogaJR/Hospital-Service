package am.initsolutions.controller;

import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.PharmacyForm;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.services.MedicineService;
import am.initsolutions.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PharmacyAdminController {
    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/pharmacyAdmin")
    public String getPharmacyPage(ModelMap modelMap) {
        List<Pharmacy> pharmacies = pharmacyService.getAll();
        modelMap.addAttribute("pharmacies", pharmacies);

        return "pharmacyAdmin";
    }

    @GetMapping("/pharmacyAdmin/add")
    public String getPharmacyAddPage() {
        return "add_pharmacy";
    }

    @PostMapping("/pharmacyAdmin/add")
    public String addPharmacy(PharmacyForm pharmacyForm, ModelMap modelMap) {
        Pharmacy savedPharmacy = pharmacyService.add(pharmacyForm);

        if (savedPharmacy != null) {
            return "redirect:/pharmacyAdmin";
        }

        modelMap.addAttribute("error", true);
        return "redirect:/pharmacyAdmin/add";
    }

    @GetMapping("/pharmacyAdmin/delete/{id}")
    public String deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.delete(id);

        return "redirect:/pharmacyAdmin";
    }

    @GetMapping("/pharmacyAdmin/edit/{id}")
    public String getEditPharmacyPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Pharmacy pharmacy = pharmacyService.getOne(id);
        modelMap.addAttribute("pharmacy", pharmacy);

        return "editPharmacy";
    }

    @PostMapping("/pharmacyAdmin/edit")
    public String editPharmacy(Pharmacy pharmacy) {
        pharmacyService.update(pharmacy);

        return "redirect:/pharmacyAdmin";
    }

    @GetMapping("/pharmacyAdmin/addMedicine/{id}")
    public String getAddMedicinePage(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("pharmacyId", id);

        return "addMedicine";
    }

    @PostMapping("/pharmacyAdmin/addMedicine")
    public String addMedicine(MedicineForm medicineForm, ModelMap modelMap) {
        Medicine savedMedicine = medicineService.add(medicineForm);

        if (savedMedicine != null) {
            return "redirect:/pharmacyAdmin";
        }

        modelMap.addAttribute("error", true);
        return "redirect:/pharmacyAdmin/addMedicine/" + medicineForm.getPharmacyId();
    }
}
