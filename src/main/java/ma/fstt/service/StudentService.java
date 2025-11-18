package ma.fstt.service;

import ma.fstt.dto.StudentDTO;
import ma.fstt.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    List<StudentDTO> getStudentsByClassName(String className);
    List<StudentDTO> searchStudentsByName(String name);
    Student getStudentEntity(Long id);
}