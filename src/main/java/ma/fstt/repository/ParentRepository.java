package ma.fstt.repository;

import ma.fstt.entity.Parent;
import ma.fstt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findByStudent(Student student);

    List<Parent> findByLastNameContainingIgnoreCase(String lastName);
}