package am.initsolutions.controller;

import am.initsolutions.dto.*;
import am.initsolutions.forms.ComplaintsForm;
import am.initsolutions.models.*;
import am.initsolutions.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PatientController {
    @Autowired
    private PatientHistoryService patientHistoryService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyMedicineService pharmacyMedicineService;

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping("/patient/{id}/history")
    public String getDiseaseHistoryPage(@PathVariable("id") Long id, ModelMap modelMap) {
        List<PatientHistory> patientHistories = patientHistoryService.getAllByPatientId(id);
        List<PatientHistoryDto> patientHistoryDtos = new ArrayList<>();
        for (PatientHistory patientHistory : patientHistories) {
            PatientHistoryDto patientHistoryDto = PatientHistoryDto.from(patientHistory);
            patientHistoryDtos.add(patientHistoryDto);
        }
        modelMap.addAttribute("patientHistories", patientHistoryDtos);

        //TODO

        return "patientHistory";
    }

    @GetMapping("/patient/{id}/edit")
    public String getEditPatientPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Patient patient = patientService.get(id);
        modelMap.addAttribute("patient", patient);

        return "editPatient";
    }

    @PostMapping("/patient/{id}/edit")
    public String editPatient(@PathVariable("id") Long id, Patient patient, RedirectAttributes attributes) {
        Patient changedPatient = patientService.update(patient);
        attributes.addFlashAttribute("userId", changedPatient.getUser().getId());

        return "redirect:/patient";
    }

    @GetMapping("/patient/{id}/doctors")
    public String getDoctors(@PathVariable("id") Long id, ModelMap modelMap) {
        List<Doctor> doctors = patientService.getDoctors(id);
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDtos.add(DoctorDto.from(doctor));
        }

        modelMap.addAttribute("doctors", doctorDtos);
        return "doctorList";
    }

    @GetMapping("/patient/{id}/register")
    public String register(@PathVariable("id") Long id, ModelMap modelMap) {
        List<Doctor> doctors = doctorService.getAll();
        List<DoctorDtoForPatient> doctorDtoForPatients = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDtoForPatients.add(DoctorDtoForPatient.from(doctor));
        }
        modelMap.addAttribute("doctors", doctorDtoForPatients);
        modelMap.addAttribute("patientId", id);

        return "chooseDoctor";
    }

    @PostMapping("/patient/{id}/register")
    public String registerForConsultation(@PathVariable("id") Long id, ComplaintsForm complaintsForm, HttpServletRequest request) {
        patientHistoryService.add(id, complaintsForm);
        request.getSession().setAttribute("registered", true);

        return "redirect:/patient";
    }

    @GetMapping("/patient/{id}/order")
    public String getOrderMedicinePage(@PathVariable("id") Long id, ModelMap modelMap) {
        List<Medicine> medicines = medicineService.getAll();
        Map<Medicine, List<OrderMedicineDto>> medicinesWithPharmacies = new HashMap<>();
        for (Medicine medicine : medicines) {
            List<OrderMedicineDto> orderMedicineDtos = new ArrayList<>();
            List<PharmacyMedicine> pharmacyMedicines = pharmacyMedicineService.getAllByMedicineId(medicine.getId());
            if (!pharmacyMedicines.isEmpty()) {
                for (PharmacyMedicine pharmacyMedicine : pharmacyMedicines) {
                    OrderMedicineDto orderMedicineDto = OrderMedicineDto.from(pharmacyMedicine);
                    orderMedicineDtos.add(orderMedicineDto);
                }

                medicinesWithPharmacies.put(medicine, orderMedicineDtos);
            }
        }

        List<Pharmacy> pharmacies = pharmacyService.getAll();
        List<String> pharmacyNames = new ArrayList<>();
        for (Pharmacy pharmacy : pharmacies) {
            pharmacyNames.add(pharmacy.getName());
        }

        modelMap.addAttribute("pharmacyNames", pharmacyNames);
        modelMap.addAttribute("medicinesWithPharmacies", medicinesWithPharmacies);

        return "orderMedicine";
    }
}
