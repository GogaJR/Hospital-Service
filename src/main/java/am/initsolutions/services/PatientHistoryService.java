package am.initsolutions.services;

import am.initsolutions.forms.ComplaintsForm;
import am.initsolutions.models.PatientHistory;

import java.util.List;

public interface PatientHistoryService {
    boolean add(Long patientId, ComplaintsForm complaintsForm);
    PatientHistory get(Long historyId);
    List<PatientHistory> getAllByPatientId(Long patientId);
    void update(PatientHistory patientHistory);
}
