import javax.persistence.*;


@Entity
@Table(name = "STUDENT")
public class Student {
    @Column(name = "Name")
    private String name;
    @Id
    @Column(name = "Student_number")
    private String studentNumber;
    @Column(name = "Class")
    private String studentClass;
    @Column(name = "Major")
    private String major;

    public Student(String name, String studentNumber, String studentClass, String major) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.studentClass = studentClass;
        this.major = major;
    }
    //Default constructor
    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }


    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "STUDENT{" +
                "name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
