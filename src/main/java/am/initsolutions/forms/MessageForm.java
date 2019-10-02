package am.initsolutions.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageForm {
    private Long patientId;
    private Long doctorId;
    private Long senderId;
    private String sender;
    private String message;
}
