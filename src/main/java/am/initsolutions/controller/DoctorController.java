package am.initsolutions.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientHistoryService patientHistoryService;

    @GetMapping("/patientListByDoctorId")
    public String patientListByDoctorId(@RequestParam("doctorId") Long doctorId, ModelMap model){
        List<Patient> patientList= patientService.patientListByDoctorId(doctorId);
        model.addAttribute("patientList",patientList);
        return "patientListByDoctor";
    }

    @GetMapping("/patientHistoryListByPatientId")
    public String patientHistoryListByPatientId(@RequestParam("patientId") Long patientId, ModelMap model){
        List<PatientHistory> patientHistoryList= patientHistoryService.getAllByPatientId(patientId);
        model.addAttribute("patientHistoryList",patientHistoryList);
        return "patientHistoryListByPatientId";
    }
}
