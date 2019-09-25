package am.initsolutions.services;


import am.initsolutions.models.Doctor;
import am.initsolutions.models.PatientHistory;
import am.initsolutions.models.Recipe;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientHistoryService patientHistoryService;

    @Override
    public Recipe add(Long doctorId,Recipe recipe) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        recipe.setDoctor(doctor);
        recipe.setDescription(recipe.getDescription());
        return recipeRepository.save(recipe);


    }
}
