package am.initsolutions.services;

import am.initsolutions.forms.ComplaintsForm;
import am.initsolutions.models.PatientHistory;

import java.util.List;

public interface PatientHistoryService {
    void add(Long patientId, ComplaintsForm complaintsForm);
    List<PatientHistory> getAllByPatientId(Long patientId);
}
