package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalForm {
    private String name;
    private String executive;
    private String address;
    private String phoneNumber;
    private String site;
}
