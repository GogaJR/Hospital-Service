package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineFormWithId {
    private Long id;
    private String name;
    private int count;
    private Long pharmacyId;
}
