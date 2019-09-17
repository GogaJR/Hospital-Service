package am.initsolutions.dto;

import am.initsolutions.models.PatientHistory;
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

    public static PatientHistoryDto from(PatientHistory patientHistory) {
        return PatientHistoryDto.builder()
                .id(patientHistory.getId())
                .complaints(patientHistory.getComplaints())
                .diagnose(patientHistory.getDiagnose())
                .build();
    }
}
