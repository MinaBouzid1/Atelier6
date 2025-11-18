package ma.fstt.controller;

import ma.fstt.dto.AbsenceDTO;
import ma.fstt.dto.StudentDTO;
import ma.fstt.service.AbsenceService;
import ma.fstt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listAbsences(Model model) {
        List<AbsenceDTO> absences = absenceService.getAllAbsences();
        model.addAttribute("absences", absences);

        // Ajouter les statistiques pour l'affichage
        long totalAbsences = absences.size();
        long justifiedAbsences = absences.stream().filter(AbsenceDTO::isJustified).count();
        model.addAttribute("totalAbsences", totalAbsences);
        model.addAttribute("justifiedAbsences", justifiedAbsences);

        return "absences/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("absence", new AbsenceDTO());
        model.addAttribute("students", students);
        return "absences/create";
    }

    @PostMapping("/create")
    public String createAbsence(@ModelAttribute AbsenceDTO absenceDTO) {
        absenceService.createAbsence(absenceDTO);
        return "redirect:/absences";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AbsenceDTO absence = absenceService.getAbsenceById(id);
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("absence", absence);
        model.addAttribute("students", students);
        return "absences/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAbsence(@PathVariable Long id, @ModelAttribute AbsenceDTO absenceDTO) {
        absenceService.updateAbsence(id, absenceDTO);
        return "redirect:/absences";
    }

    @GetMapping("/delete/{id}")
    public String deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
        return "redirect:/absences";
    }

    @GetMapping("/student/{studentId}")
    public String getAbsencesByStudent(@PathVariable Long studentId, Model model) {
        List<AbsenceDTO> absences = absenceService.getAbsencesByStudent(studentId);
        StudentDTO student = studentService.getStudentById(studentId);
        model.addAttribute("absences", absences);
        model.addAttribute("student", student);
        return "absences/list";
    }

    @GetMapping("/date")
    public String getAbsencesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    Model model) {
        List<AbsenceDTO> absences = absenceService.getAbsencesByDate(date);
        model.addAttribute("absences", absences);
        model.addAttribute("selectedDate", date);
        return "absences/list";
    }

    @GetMapping("/justify/{id}")
    public String justifyAbsence(@PathVariable Long id, @RequestParam boolean justified) {
        absenceService.justifyAbsence(id, justified);
        return "redirect:/absences";
    }

    @GetMapping("/search")
    public String searchAbsences(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                 Model model) {
        if (startDate != null && endDate != null) {
            List<AbsenceDTO> absences = absenceService.getAbsencesBetweenDates(startDate, endDate);
            model.addAttribute("absences", absences);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        }
        return "absences/search";
    }
}