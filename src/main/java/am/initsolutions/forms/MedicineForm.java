package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineForm {
    private Long medicineId;
    private Long pharmacyId;
}
