package am.initsolutions.services;

import am.initsolutions.models.Recipe;

public interface RecipeService {
    Recipe add(Long doctorId,Recipe recipe);

}
