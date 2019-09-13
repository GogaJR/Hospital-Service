package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyForm {
    private String name;
    private String address;
    private String phoneNumber;
    private String openAt;
    private String closeAt;
}
