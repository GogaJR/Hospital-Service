package am.initsolutions.services;

import am.initsolutions.models.userParentModel.ParentModel;
import org.springframework.messaging.simp.user.SimpUser;

import java.util.Set;

public interface ChatService {
    void sendMessage(String to, Long patientId, Long doctorId, String from, String message);
    void sendMessage(String to, Long patientId, Long doctorId, String message);
    boolean findUserAndSendMessage(Set<SimpUser> users, ParentModel model, Long patientId, Long doctorId, String from, String message);
}
