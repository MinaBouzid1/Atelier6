package ma.fstt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "absences")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "absence_date", nullable = false)
    private LocalDate date;

    @Column(name = "reason")
    private String reason;

    @Column(name = "justified")
    private boolean justified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Constructors
    public Absence() {}

    public Absence(LocalDate date, String reason, boolean justified, Student student) {
        this.date = date;
        this.reason = reason;
        this.justified = justified;
        this.student = student;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public boolean isJustified() { return justified; }
    public void setJustified(boolean justified) { this.justified = justified; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}