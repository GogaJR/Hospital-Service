package am.initsolutions.controller;

import am.initsolutions.forms.PharmacyForm;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.services.MedicineService;
import am.initsolutions.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ModeratorController {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/moderator")
    public String getModeratorPage(@ModelAttribute("added") Boolean added, ModelMap modelMap, HttpServletRequest request) {
        List<Pharmacy> pharmacies = pharmacyService.getAll();
        modelMap.addAttribute("pharmacies", pharmacies);

        if (added != null) {
            modelMap.addAttribute("added", added);
            request.getSession().setAttribute("added", false);
        }

        return "moderator";
    }

    @GetMapping("/moderator/addPharmacy")
    public String getPharmacyAddPage() {
        return "add_pharmacy";
    }

    @PostMapping("/moderator/addPharmacy")
    public String addPharmacy(PharmacyForm pharmacyForm, HttpServletRequest request) {
        Pharmacy savedPharmacy = pharmacyService.add(pharmacyForm);

        if (savedPharmacy != null) {
            request.getSession().setAttribute("added", true);
            return "redirect:/moderator";
        }

        return "redirect:/moderator/addPharmacy";
    }

    @GetMapping("/moderator/delete/{id}")
    public String deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.delete(id);

        return "redirect:/moderator";
    }

    @GetMapping("/moderator/edit/{id}")
    public String getEditPharmacyPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Pharmacy pharmacy = pharmacyService.getOne(id);
        modelMap.addAttribute("pharmacy", pharmacy);

        return "editPharmacy";
    }

    @PostMapping("/moderator/edit")
    public String editPharmacy(Pharmacy pharmacy) {
        pharmacyService.update(pharmacy);

        return "redirect:/moderator";
    }

    @GetMapping("/moderator/medicines")
    public String getMedicinesPage(ModelMap modelMap) {
        List<Medicine> medicines = medicineService.getAll();
        modelMap.addAttribute("medicines", medicines);

        return "medicineListForModerator";
    }

    @GetMapping("/moderator/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Long id) {
        medicineService.delete(id);

        return "redirect:/moderator/medicines";
    }

    @GetMapping("/moderator/medicines/edit/{id}")
    public String getEditMedicinePage(@PathVariable("id") Long id, ModelMap modelMap) {
        Medicine medicine = medicineService.getOne(id);
        modelMap.addAttribute("medicine", medicine);

        return "editMedicineForModerator";
    }

    @PostMapping("/moderator/medicines/edit")
    public String editMedicine(Medicine medicine) {
        medicineService.update(medicine);

        return "redirect:/moderator/medicines";
    }

    @GetMapping("/moderator/medicines/add")
    public String getAddMedicinePage() {
        return "addMedicine";
    }

    @PostMapping("/moderator/medicines/add")
    public String addMedicine(String name) {
        medicineService.add(name);

        return "redirect:/moderator/medicines";
    }
}
