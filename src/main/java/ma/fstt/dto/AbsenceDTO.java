package ma.fstt.dto;

import java.time.LocalDate;

public class AbsenceDTO {
    private Long id;
    private LocalDate date;
    private String reason;
    private boolean justified;
    private Long studentId;
    private String studentName;
    private String className;

    // Constructors
    public AbsenceDTO() {}

    public AbsenceDTO(Long id, LocalDate date, String reason, boolean justified, Long studentId, String studentName, String className) {
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.justified = justified;
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
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

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}