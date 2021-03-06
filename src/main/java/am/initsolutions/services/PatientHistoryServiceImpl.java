package am.initsolutions.services;

import am.initsolutions.forms.ComplaintsForm;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Patient;
import am.initsolutions.models.PatientHistory;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.PatientHistoryRepository;
import am.initsolutions.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService {

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public boolean add(Long patientId, ComplaintsForm complaintsForm) {
        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(complaintsForm.getDoctorId());

        if (!patient.getDoctors().contains(doctor)) {
            PatientHistory patientHistory = PatientHistory.builder()
                    .patient(patient)
                    .doctor(doctor)
                    .complaints(complaintsForm.getComplaints())
                    .build();
            patientHistoryRepository.save(patientHistory);
            patientService.setDoctor(patient, doctor);

            return true;
        }

        return false;
    }

    @Override
    public PatientHistory get(Long historyId) {
        return patientHistoryRepository.findOne(historyId);
    }

    @Override
    public List<PatientHistory> getAllByPatientId(Long patientId) {
        return patientHistoryRepository.findAllByPatientId(patientId);
    }

    @Override
    public void update(PatientHistory patientHistory) {
        PatientHistory pHistory = patientHistoryRepository.findOne(patientHistory.getId());
        pHistory.setDiagnose(patientHistory.getDiagnose());
        patientHistoryRepository.save(pHistory);
    }

}
