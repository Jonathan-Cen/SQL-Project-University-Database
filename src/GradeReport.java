import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "GRADE_REPORT")
public class GradeReport implements Serializable {
    @Id
    @Column(name = "Student_number")
    private String studentNumber;
    @Id
    @Column(name = "Section_identifier")
    private int sectionIdentifier;
    @Column(name = "Grade")
    private String grade;

    public GradeReport(String studentNumber, int sectionIdentifier, String grade) {
        this.studentNumber = studentNumber;
        this.sectionIdentifier = sectionIdentifier;
        this.grade = grade;
    }

    public GradeReport(){}

    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getSectionIdentifier() {
        return sectionIdentifier;
    }

    public void setSectionIdentifier(int sectionIdentifier) {
        this.sectionIdentifier = sectionIdentifier;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "GradeReport{" +
                "studentNumber='" + studentNumber + '\'' +
                ", sectionIdentifier=" + sectionIdentifier +
                ", grade='" + grade + '\'' +
                '}';
    }
}
