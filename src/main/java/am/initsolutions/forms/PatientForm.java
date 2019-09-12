package am.initsolutions.forms;

import am.initsolutions.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientForm {
    private String name;
    private String surname;
    private int age;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String mail;
    private String password;
}
