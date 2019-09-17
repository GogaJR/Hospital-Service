package am.initsolutions.services;

import am.initsolutions.models.PatientHistory;

import java.util.List;

public interface PatientHistoryService {
    List<PatientHistory> getAllByPatientId(Long patientId);
}
