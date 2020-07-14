import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateTables {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlserver://localhost;databaseName=UNIVERSITY;integratedSecurity=true";

        try {
            //load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
            //Establish a connection
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Database connected\n");
            // Creates a statement
            Statement stmt = connection.createStatement();
            ArrayList<String> commands = new ArrayList<>();

            String createStudentTable =
                    "CREATE TABLE STUDENT( " +
                    "Name VARCHAR(30) NOT NULL," +
                    "Student_number VARCHAR(10) NOT NULL," +
                    "Class VARCHAR(5) NOT NULL," +
                    "Major VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY(Student_number));";
            commands.add(createStudentTable);

            String createCourseTable = "CREATE TABLE COURSE(" +
                    "Course_name VARCHAR(30) NOT NULL," +
                    "Course_number VARCHAR(20) NOT NULL," +
                    "Credit_hours INT NOT NULL," +
                    "Department VARCHAR(30) NOT NULL," +
                    "PRIMARY KEY(Course_number) );";
            commands.add(createCourseTable);

            String createPrerequisiteTable = "CREATE TABLE PREREQUISITE(" +
                    "Course_number VARCHAR(20) NOT NULL," +
                    "Prerequisite_number VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (Course_number, Prerequisite_number)," +
                    "FOREIGN KEY (Course_number) REFERENCES COURSE(Course_number)," +
                    "FOREIGN KEY (Prerequisite_number) REFERENCES COURSE(Course_number) );";
            commands.add(createPrerequisiteTable);

            String createSectionTable = "CREATE TABLE SECTION(" +
                    "Section_identifier INT NOT NULL," +
                    "Course_number VARCHAR(20) NOT NULL," +
                    "Semester VARCHAR(10) NOT NULL," +
                    "Year VARCHAR(4) NOT NULL," +
                    "Instructor VARCHAR(30) NOT NULL," +
                    "PRIMARY KEY (Section_identifier)," +
                    "FOREIGN KEY(Course_number) REFERENCES COURSE(Course_number) );";
            commands.add(createSectionTable);

            String createGradeReportTable = "CREATE TABLE GRADE_REPORT(" +
                    "Student_number VARCHAR(10) NOT NULL," +
                    "Section_identifier INT NOT NULL," +
                    "Grade VARCHAR(2) NOT NULL," +
                    "PRIMARY KEY (Student_number,Section_identifier)," +
                    "FOREIGN KEY (Student_number) REFERENCES STUDENT(Student_number)," +
                    "FOREIGN KEY (Section_identifier) REFERENCES SECTION(Section_identifier) );";
            commands.add(createGradeReportTable);

            for(String command : commands){stmt.execute(command);}

            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
