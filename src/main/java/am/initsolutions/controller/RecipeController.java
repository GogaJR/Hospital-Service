package am.initsolutions.controller;

import am.initsolutions.repository.RecipeRepository;
import am.initsolutions.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;





}
