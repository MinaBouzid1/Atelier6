package ma.fstt.service;

import ma.fstt.dto.AbsenceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceService {
    List<AbsenceDTO> getAllAbsences();
    AbsenceDTO getAbsenceById(Long id);
    AbsenceDTO createAbsence(AbsenceDTO absenceDTO);
    AbsenceDTO updateAbsence(Long id, AbsenceDTO absenceDTO);
    void deleteAbsence(Long id);
    List<AbsenceDTO> getAbsencesByStudent(Long studentId);
    List<AbsenceDTO> getAbsencesByDate(LocalDate date);
    List<AbsenceDTO> getAbsencesByClassNameAndDate(String className, LocalDate date);
    List<AbsenceDTO> getAbsencesBetweenDates(LocalDate startDate, LocalDate endDate);
    void justifyAbsence(Long id, boolean justified);
}