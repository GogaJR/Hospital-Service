package am.initsolutions.controller;

import am.initsolutions.dto.DoctorDto;
import am.initsolutions.dto.DoctorDtoForPatient;
import am.initsolutions.dto.PatientHistoryDto;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Patient;
import am.initsolutions.models.PatientHistory;
import am.initsolutions.services.DoctorService;
import am.initsolutions.services.PatientHistoryService;
import am.initsolutions.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientHistoryService patientHistoryService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

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
    public String registerForConsultation(@PathVariable("id") Long id, String doctorId, String complaints) {
        System.out.println(doctorId + " " + complaints);
        return "redirect:/patient";
    }
}
