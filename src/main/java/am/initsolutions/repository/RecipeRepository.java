package am.initsolutions.repository;

import am.initsolutions.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
     @Transactional
    @Modifying
    @Query(value ="INSERT INTO recipe(doctorId, description) VALUES(:doctorId,:description)",
            nativeQuery = true)
    Long saveRecipe(@Param("doctorId") Long doctorId);

//    @Transactional
//    @Modifying
//    @Query(value ="INSERT INTO recipe_medicine(recipe_id,medicine_id) VALUES(:recipeId,:medicineId)",
//            nativeQuery = true)
//    void recipeMedicine(@Param("recipeId") Long recipeId,@Param("medicineId")Long medicineId);
}
