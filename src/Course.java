import javax.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {
    @Column(name = "Course_name")
    private String courseName;
    @Id
    @Column(name = "Course_number")
    private String courseNumber;
    @Column(name = "Credit_hours")
    private int creditHours;
    @Column(name = "Department")
    private String department;


    public Course(String courseName, String courseNumber, int creditHours, String department) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.department = department;
    }

    //default constructor
    public Course(){}

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", creditHours=" + creditHours +
                ", department='" + department + '\'' +
                '}';
    }
}
