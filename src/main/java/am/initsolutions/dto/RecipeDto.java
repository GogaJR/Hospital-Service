package am.initsolutions.dto;

import am.initsolutions.models.Medicine;
import am.initsolutions.models.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {
    private String description;
    private List<Medicine> medicines;

    public static RecipeDto from(Recipe recipe) {
        return recipe == null ? null : RecipeDto.builder()
                .description(recipe.getDescription())
                .medicines(recipe.getMedicines())
                .build();
    }
}
