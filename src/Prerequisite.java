import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PREREQUISITE")
public class Prerequisite implements Serializable {
    @Id
    @Column(name = "Course_number")
    private String courseNumber;
    @Id
    @Column(name = "Prerequisite_number")
    private String prerequisiteNumber;

    public Prerequisite(String courseNumber, String prerequisiteNumber) {
        this.courseNumber = courseNumber;
        this.prerequisiteNumber = prerequisiteNumber;
    }

    public Prerequisite(){}

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getPrerequisiteNumber() {
        return prerequisiteNumber;
    }

    public void setPrerequisiteNumber(String prerequisiteNumber) {
        this.prerequisiteNumber = prerequisiteNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Prerequisite{" +
                "courseNumber='" + courseNumber + '\'' +
                ", prerequisiteNumber='" + prerequisiteNumber + '\'' +
                '}';
    }
}
