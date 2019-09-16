package am.initsolutions.controller;

import am.initsolutions.forms.HospitalForm;
import am.initsolutions.models.Hospital;
import am.initsolutions.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainAdminController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/mainAdmin")
    public String hospitalAction() {
        return "redirect:/mainAdmin/add";
    }

    @GetMapping("/mainAdmin/add")
    public String getAddHospitalPage() {
        return "addHospital";
    }

    @PostMapping("/mainAdmin/add")
    public String addHospital(HospitalForm hospitalForm, ModelMap modelMap) {
        Hospital savedHospital = hospitalService.add(hospitalForm);

        if (savedHospital != null) {
            return "redirect:/mainAdmin";
        }

        modelMap.addAttribute("error", true);
        return "redirect:/mainAdmin/add";
    }

    @GetMapping("/mainAdmin/delete/{id}")
    public String deleteHospital(@PathVariable("id") Long id) {
        hospitalService.delete(id);

        return "redirect:/mainAdmin";
    }

    @GetMapping("/mainAdmin/edit/{id}")
    public String getEditHospitalPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Hospital hospital = hospitalService.get(id);
        modelMap.addAttribute("hospital", hospital);

        return "editHospital";
    }

    @PostMapping("/mainAdmin/edit")
    public String editHospital(Hospital hospital) {
        hospitalService.update(hospital);

        return "redirect:/mainAdmin";
    }
}
