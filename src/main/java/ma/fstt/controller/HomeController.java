package ma.fstt.controller;

import ma.fstt.dto.AbsenceDTO;
import ma.fstt.dto.StudentDTO;
import ma.fstt.service.StudentService;
import ma.fstt.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AbsenceService absenceService;

    @GetMapping("/")
    public String home(Model model) {
        try {
            // Récupérer les statistiques
            List<StudentDTO> students = studentService.getAllStudents();
            List<AbsenceDTO> absences = absenceService.getAllAbsences();

            long totalAbsences = absences.size();
            long justifiedAbsences = absences.stream().filter(AbsenceDTO::isJustified).count();

            model.addAttribute("totalStudents", students.size());
            model.addAttribute("totalAbsences", totalAbsences);
            model.addAttribute("justifiedAbsences", justifiedAbsences);

        } catch (Exception e) {
            // En cas d'erreur (base vide), mettre des valeurs par défaut
            model.addAttribute("totalStudents", 0);
            model.addAttribute("totalAbsences", 0);
            model.addAttribute("justifiedAbsences", 0);
        }

        return "home";
    }
}