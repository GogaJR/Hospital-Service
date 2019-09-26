package am.initsolutions.repository;

import am.initsolutions.models.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {
    List<PatientHistory> findAllByPatientId(Long patientId);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE patient_history,recipe SET recipe_id=:recipeId  WHERE  patient_history.doctor_id=recipe.doctor_id;")
    void updateRecipeId(@Param("recipeId") Long recipeId);



}
