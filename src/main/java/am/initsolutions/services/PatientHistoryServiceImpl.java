package am.initsolutions.services;

import am.initsolutions.models.PatientHistory;
import am.initsolutions.repository.PatientHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService {

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;

    @Override
    public List<PatientHistory> getAllByPatientId(Long patientId) {
        return patientHistoryRepository.findAllByPatientId(patientId);
    }


}
