package am.initsolutions.controller;

import am.initsolutions.models.*;
import am.initsolutions.repository.PatientHistoryRepository;
import am.initsolutions.repository.RecipeRepository;
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

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/patientListByDoctorId")
    public String patientListByDoctorId(@RequestParam("doctorId") Long doctorId, ModelMap model){
        List<Patient> patientList= patientService.patientListByDoctorId(doctorId);
        model.addAttribute("patientList",patientList);
        model.addAttribute("doctorId",doctorId);
        return "patientListByDoctor";
    }

    @GetMapping("/patientHistoryListByPatientId")
    public String patientHistoryListByPatientId(@RequestParam("patientId") Long patientId, ModelMap model){
        List<PatientHistory> patientHistoryList= patientHistoryService.getAllByPatientId(patientId);
        model.addAttribute("patientHistoryList",patientHistoryList);
        model.addAttribute("patientId", patientId);
        return "patientHistoryListByPatientId";
    }

    @GetMapping("/addDiagnoseByPatientId")
    public String editPatientHistory(@RequestParam("id") Long id,ModelMap modelMap) {
        PatientHistory patientHistory = patientHistoryService.get(id);
        modelMap.addAttribute("patientHistory", patientHistory);
        List<Medicine> medicines=medicineService.getAll();
        modelMap.addAttribute("medicines", medicines);
        modelMap.addAttribute("id", id);
        return "diagnosePage";
    }

    @PostMapping("/addDiagnoseByPatientId")
    public String editPatientHistory(PatientHistory patientHistory,Long patientId) {
       patientHistoryService.update(patientHistory);
       return "redirect:/patientHistoryListByPatientId?patientId="+patientId;
    }

    @PostMapping("/addRecipe")
    public String addRecipe(Long doctorId,Recipe recipe){
        Recipe recipe1 =  recipeService.add(doctorId,recipe);
        if (recipe1 != null) {
            patientHistoryRepository.updateRecipeId(recipe1.getId());
            return "redirect:/doctorPage";
        }
        return "redirect:/doctorPage";

    }

}
