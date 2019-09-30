package am.initsolutions.controller.rest;

import am.initsolutions.forms.MessageForm;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.services.DoctorService;
import am.initsolutions.services.PatientService;
import am.initsolutions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Long patientId;
    private Long doctorId;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    public ChatController() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @PostMapping(value = "/send", consumes = "application/json")
    public ResponseEntity send(@RequestBody MessageForm message) {
        Long patientId = message.getPatientId();
        Long doctorId = message.getDoctorId();

        if (this.patientId == null || this.doctorId == null) {
            this.patientId = patientId;
            this.doctorId = doctorId;
        }

        if (this.patientId == patientId && this.doctorId == doctorId) {
            User loggedInUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            if (loggedInUser.getAuthorities().contains(new SimpleGrantedAuthority(UserType.DOCTOR.name()))) {
                Optional<User> foundPatient = sessionRegistry.getAllPrincipals()
                        .stream()
                        .map(p -> (User) p)
                        .filter(user -> patientService.getByUserId(userService.getByEmail(user.getUsername()).getId()).getId() == patientId)
                        .findFirst();

                messagingTemplate.convertAndSendToUser(
                        foundPatient.get().getUsername(),
                        "/message",
                        MessageFormat.format("{0}: {1}",
                                loggedInUser.getUsername(),
                                message.getMessage())
                );
            } else if (loggedInUser.getAuthorities().contains(new SimpleGrantedAuthority(UserType.PATIENT.name()))) {
                Doctor foundDoctor = null;
                for (Object object : sessionRegistry.getAllPrincipals()) {
                    User user = (User) object;
                    Doctor doctor = doctorService.getByUserId(userService.getByEmail(user.getUsername()).getId());

                    if (doctor != null) {
                        if (doctor.getId() == doctorId) {
                            foundDoctor = doctor;
                            break;
                        }
                    }
                }

                if (foundDoctor != null) {
                    messagingTemplate.convertAndSendToUser(
                            foundDoctor.getUser().getEmail(),
                            "/message",
                            MessageFormat.format("{0}: {1}",
                                    loggedInUser.getUsername(),
                                    message.getMessage())
                    );
                }
            }
        }

        return ResponseEntity.ok().build();
    }
}
