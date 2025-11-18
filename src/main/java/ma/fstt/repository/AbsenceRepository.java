package ma.fstt.repository;

import ma.fstt.entity.Absence;
import ma.fstt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByStudent(Student student);

    List<Absence> findByDate(LocalDate date);

    List<Absence> findByJustified(boolean justified);

    @Query("SELECT a FROM Absence a WHERE a.student.className = :className AND a.date = :date")
    List<Absence> findByClassNameAndDate(@Param("className") String className, @Param("date") LocalDate date);

    @Query("SELECT a FROM Absence a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Absence> findAbsencesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}