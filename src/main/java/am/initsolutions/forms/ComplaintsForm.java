package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintsForm {
    private Long doctorId;
    private String complaints;
}
