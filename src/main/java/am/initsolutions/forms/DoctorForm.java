package am.initsolutions.forms;

import am.initsolutions.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorForm {
    private String name;
    private String surname;
    private int age;
    private Gender gender;
    private String phoneNumber;
    private int experience;
    private Long hospitalId;
    private Long departmentId;
    private String mail;
    private String password;
}
