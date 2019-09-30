package am.initsolutions.controller;

import am.initsolutions.dto.OrderDto;
import am.initsolutions.dto.PharmacyMedicineDto;
import am.initsolutions.forms.MedicineForm;
import am.initsolutions.forms.MedicineFormWithId;
import am.initsolutions.models.Medicine;
import am.initsolutions.models.Order;
import am.initsolutions.models.Pharmacy;
import am.initsolutions.models.PharmacyMedicine;
import am.initsolutions.services.*;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private PharmacyAdminService pharmacyAdminService;

    @GetMapping("/pharmacyAdmin")
    public String getPharmacyPage(ModelMap modelMap) {
        List<Pharmacy> pharmacies = pharmacyService.getAll();
        modelMap.addAttribute("pharmacies", pharmacies);

        return "pharmacyAdmin";
    }

    @GetMapping("/pharmacyAdmin/addMedicine/{id}")
    public String getAddMedicinePage(@PathVariable("id") Long id, ModelMap modelMap,
                                     @ModelAttribute("added") Boolean added, HttpServletRequest request) {
        List<Medicine> medicines = medicineService.getAll();
        modelMap.addAttribute("medicineList", medicines);
        modelMap.addAttribute("pharmacyId", id);

        if (added != null) {
            modelMap.addAttribute("added", added);
            request.getSession().setAttribute("added", false);
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

    @GetMapping("/pharmacyAdmin/orders")
    public String getOrdersPage(ModelMap modelMap) {
        List<Order> orders = orderService.getAll();
        List<OrderDto> orderDtos = pharmacyAdminService.getOrderDtos(orders);
        modelMap.addAttribute("orders", orderDtos);
        modelMap.addAttribute("index", 0);

        return "orderList";
    }
}
