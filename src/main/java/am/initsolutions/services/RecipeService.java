package am.initsolutions.services;

import am.initsolutions.models.Medicine;
import am.initsolutions.models.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe add(Long doctorId,Recipe recipe);
    //void recipe(Long recipeId, Long[] medicinesId);

}
