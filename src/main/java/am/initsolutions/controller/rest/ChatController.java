package am.initsolutions.controller.rest;

import am.initsolutions.forms.MessageForm;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Patient;
import am.initsolutions.models.User;
import am.initsolutions.services.ChatService;
import am.initsolutions.services.DoctorService;
import am.initsolutions.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private ChatService chatService;

    @PostMapping(value = "/send", consumes = "application/json")
    public ResponseEntity send(@RequestBody MessageForm message) {
        Long patientId = message.getPatientId();
        Long doctorId = message.getDoctorId();

        User messageSender;
        boolean isPatient;
        if (message.getSender().equals("doctor")) {
            messageSender = doctorService.get(message.getSenderId()).getUser();
            isPatient = false;
        } else {
            messageSender = patientService.get(message.getSenderId()).getUser();
            isPatient = true;
        }

        Set<SimpUser> users = userRegistry.getUsers();
        if (users.size() == 1) {
            if (isPatient) {
                Doctor doctor = doctorService.get(doctorId);
                chatService.sendMessage(messageSender.getEmail(), patientId, doctorId, doctor.getName());
            } else {
                Patient patient = patientService.get(patientId);
                chatService.sendMessage(messageSender.getEmail(), patientId, doctorId, patient.getName());
            }

            return ResponseEntity.ok().build();
        }

        if (isPatient) {
            Doctor doctor = doctorService.get(doctorId);
            boolean result = chatService.findUserAndSendMessage(users, doctor, patientId, doctorId, messageSender.getEmail(), message.getMessage());


            if (!result) {
                chatService.sendMessage(messageSender.getEmail(), patientId, doctorId, doctor.getName());
            }
        } else {
            Patient patient = patientService.get(patientId);
            boolean result = chatService.findUserAndSendMessage(users, patient, patientId, doctorId, messageSender.getEmail(), message.getMessage());

            if (!result) {
                chatService.sendMessage(messageSender.getEmail(), patientId, doctorId, patient.getName());
            }
        }

        return ResponseEntity.ok().build();
    }
}
