import javax.persistence.*;

@Entity
@Table(name = "SECTION")
public class Section {
    @Id
    @Column(name = "Section_identifier")
    private int sectionIdentifier;
    @Column(name = "Course_number")
    private String courseNumber;
    @Column(name = "Semester")
    private String semester;
    @Column(name = "Year")
    private String year;
    @Column(name = "Instructor")
    private String instructor;

    public Section(int sectionIdentifier, String courseNumber, String semester, String year, String instructor) {
        this.sectionIdentifier = sectionIdentifier;
        this.courseNumber = courseNumber;
        this.semester = semester;
        this.year = year;
        this.instructor = instructor;
    }
    //Default constructor
    public Section(){}

    public int getSectionIdentifier() {
        return sectionIdentifier;
    }

    public void setSectionIdentifier(int sectionIdentifier) {
        this.sectionIdentifier = sectionIdentifier;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionIdentifier='" + sectionIdentifier + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", semester='" + semester + '\'' +
                ", year='" + year + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
