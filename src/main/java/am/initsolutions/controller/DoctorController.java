package am.initsolutions.controller;

import am.initsolutions.models.*;
import am.initsolutions.repository.PatientHistoryRepository;
import am.initsolutions.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientHistoryService patientHistoryService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;

    @GetMapping("/patientListByDoctorId")
    public String patientListByDoctorId(@RequestParam("doctorId") Long doctorId, ModelMap model, HttpSession session){
        List<Patient> patientList= patientService.patientListByDoctorId(doctorId);
        session.setAttribute("doctorId",patientList);
        model.addAttribute("patientList",patientList);
        //model.addAttribute("doctorId",doctorId);
        return "patientListByDoctor";
    }

    @GetMapping("/patientHistoryListByPatientId")
    public String patientHistoryListByPatientId(@RequestParam("patientId") Long patientId, ModelMap model){
        List<PatientHistory> patientHistoryList= patientHistoryService.getAllByPatientId(patientId);
        model.addAttribute("patientHistoryList",patientHistoryList);
        return "patientHistoryListByPatientId";
    }

    @GetMapping("/addDiagnoseByPatientId")
    public String editPatientHistory(@RequestParam("id") Long id,ModelMap modelMap,HttpSession session) {
        PatientHistory patientHistory = patientHistoryService.get(id);
        modelMap.addAttribute("patientHistory", patientHistory);
        session.setAttribute("patientId", patientHistory.getPatient().getId());
        Long patientId= (Long) session.getAttribute("patientId");
        return "diagnosePage";
    }

    @PostMapping("/addDiagnoseByPatientId")
    public String editPatientHistory(PatientHistory patientHistory,Long patientId) {
       patientHistoryService.update(patientHistory);
       return "redirect:/patientHistoryListByPatientId?patientId="+patientId;
    }

    @PostMapping("/addRecipe")
    public String addRecipe(Long doctorId,Recipe recipe,Long patientId,Long id){
        Recipe recipe1 =  recipeService.add(doctorId,recipe);

        if (recipe1 != null) {
           patientHistoryRepository.updateRecipeId(recipe1.getId());
            return "redirect:/patientHistoryListByPatientId?patientId="+patientId;
        }
        return "redirect:/addDiagnoseByPatientId?id="+id;

    }

}
