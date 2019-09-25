package am.initsolutions.dto;

import am.initsolutions.models.PatientHistory;
import am.initsolutions.models.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientHistoryDto {
    private Long id;
    private String complaints;
    private String diagnose;
    private DoctorDto doctorDto;
    private RecipeDto recipeDto;

    public static PatientHistoryDto from(PatientHistory patientHistory) {
        return PatientHistoryDto.builder()
                .id(patientHistory.getId())
                .complaints(patientHistory.getComplaints())
                .diagnose(patientHistory.getDiagnose())
                .doctorDto(DoctorDto.from(patientHistory.getDoctor()))
                .recipeDto(RecipeDto.from(patientHistory.getRecipe()))
                .build();
    }
}
