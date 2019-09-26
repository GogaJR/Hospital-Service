package am.initsolutions.forms;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderForm {
    List<String> pharmacies;
    List<String> medicines;
    List<String> addresses;
}
