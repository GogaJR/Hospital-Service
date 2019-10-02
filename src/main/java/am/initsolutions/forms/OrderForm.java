package am.initsolutions.forms;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
    List<String> pharmacies;
    List<String> medicines;
    List<String> addresses;
    List<String> counts;
}
