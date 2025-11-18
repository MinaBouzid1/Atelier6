package ma.fstt.controller;

import ma.fstt.dto.StudentDTO;
import ma.fstt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "students/create";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam String name, Model model) {
        if (name != null && !name.trim().isEmpty()) {
            List<StudentDTO> students = studentService.searchStudentsByName(name);
            model.addAttribute("students", students);
            model.addAttribute("searchTerm", name);
        } else {
            // Si la recherche est vide, retourner à la liste complète
            return "redirect:/students";
        }
        return "students/list"; // Retourner la même vue que la liste normale
    }

    @GetMapping("/class/{className}")
    public String getStudentsByClass(@PathVariable String className, Model model) {
        List<StudentDTO> students = studentService.getStudentsByClassName(className);
        model.addAttribute("students", students);
        model.addAttribute("className", className);
        return "students/list";
    }
}