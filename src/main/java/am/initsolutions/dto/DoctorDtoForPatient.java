package am.initsolutions.dto;

import am.initsolutions.models.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDtoForPatient {
    private Long id;
    private String name;
    private String surname;
    private String departmentName;
    private String hospitalName;
    private int experience;

    public static DoctorDtoForPatient from(Doctor doctor) {
        return DoctorDtoForPatient.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .departmentName(doctor.getDepartment().getName())
                .hospitalName(doctor.getHospital().getName())
                .experience(doctor.getExperience())
                .build();
    }
}
