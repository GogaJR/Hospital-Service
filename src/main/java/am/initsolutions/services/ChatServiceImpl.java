package am.initsolutions.services;

import am.initsolutions.models.Doctor;
import am.initsolutions.models.Patient;
import am.initsolutions.models.userParentModel.ParentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(String to, Long patientId, Long doctorId, String from, String message) {
        messagingTemplate.convertAndSendToUser(
                to,
                "/message/" + patientId + "&" + doctorId,
                MessageFormat.format("{0}: {1}",
                        from,
                        message)
        );
    }

    @Override
    public void sendMessage(String to, Long patientId, Long doctorId, String message) {
        messagingTemplate.convertAndSendToUser(
                to,
                "/message/" + patientId + "&" + doctorId,
                MessageFormat.format("{0}",
                        message)
        );
    }

    @Override
    public boolean findUserAndSendMessage(Set<SimpUser> users, ParentModel model, Long patientId, Long doctorId, String from, String message) {
        if (model.getClass().equals(Doctor.class)) {
            Doctor doctor = (Doctor) model;
            for (SimpUser user : users) {
                if (user.getName().equals(doctor.getUser().getEmail())) {
                    sendMessage(user.getName(), patientId, doctorId, from, message);

                    return true;
                }
            }
        } else {
            Patient patient = (Patient) model;
            for (SimpUser user : users) {
                if (user.getName().equals(patient.getUser().getEmail())) {
                    sendMessage(user.getName(), patientId, doctorId, from, message);

                    return true;
                }
            }
        }

        return false;
    }
}
