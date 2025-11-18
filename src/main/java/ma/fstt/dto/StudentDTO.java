package ma.fstt.dto;

import java.time.LocalDate;

public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String className;

    // Constructors
    public StudentDTO() {}

    public StudentDTO(Long id, String firstName, String lastName, LocalDate birthDate, String className) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.className = className;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}