package ma.fstt.service.impl;

import ma.fstt.dto.AbsenceDTO;
import ma.fstt.entity.Absence;
import ma.fstt.entity.Student;
import ma.fstt.repository.AbsenceRepository;
import ma.fstt.service.AbsenceService;
import ma.fstt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private StudentService studentService;

    @Override
    public List<AbsenceDTO> getAllAbsences() {
        return absenceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AbsenceDTO getAbsenceById(Long id) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));
        return convertToDTO(absence);
    }

    @Override
    public AbsenceDTO createAbsence(AbsenceDTO absenceDTO) {
        Student student = studentService.getStudentEntity(absenceDTO.getStudentId());
        Absence absence = convertToEntity(absenceDTO, student);
        Absence savedAbsence = absenceRepository.save(absence);
        return convertToDTO(savedAbsence);
    }

    @Override
    public AbsenceDTO updateAbsence(Long id, AbsenceDTO absenceDTO) {
        Absence existingAbsence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));

        Student student = studentService.getStudentEntity(absenceDTO.getStudentId());

        existingAbsence.setDate(absenceDTO.getDate());
        existingAbsence.setReason(absenceDTO.getReason());
        existingAbsence.setJustified(absenceDTO.isJustified());
        existingAbsence.setStudent(student);

        Absence updatedAbsence = absenceRepository.save(existingAbsence);
        return convertToDTO(updatedAbsence);
    }

    @Override
    public void deleteAbsence(Long id) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));
        absenceRepository.delete(absence);
    }

    @Override
    public List<AbsenceDTO> getAbsencesByStudent(Long studentId) {
        Student student = studentService.getStudentEntity(studentId);
        return absenceRepository.findByStudent(student).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByDate(LocalDate date) {
        return absenceRepository.findByDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByClassNameAndDate(String className, LocalDate date) {
        return absenceRepository.findByClassNameAndDate(className, date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return absenceRepository.findAbsencesBetweenDates(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void justifyAbsence(Long id, boolean justified) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));
        absence.setJustified(justified);
        absenceRepository.save(absence);
    }

    private AbsenceDTO convertToDTO(Absence absence) {
        return new AbsenceDTO(
                absence.getId(),
                absence.getDate(),
                absence.getReason(),
                absence.isJustified(),
                absence.getStudent().getId(),
                absence.getStudent().getFullName(),
                absence.getStudent().getClassName()
        );
    }

    private Absence convertToEntity(AbsenceDTO absenceDTO, Student student) {
        return new Absence(
                absenceDTO.getDate(),
                absenceDTO.getReason(),
                absenceDTO.isJustified(),
                student
        );
    }
}