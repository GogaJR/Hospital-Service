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
public class DoctorDto {
    private String name;
    private String surname;
    private String phoneNumber;

    public static DoctorDto from(Doctor doctor) {
        return DoctorDto.builder()
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phoneNumber(doctor.getPhoneNumber())
                .build();
    }
}
