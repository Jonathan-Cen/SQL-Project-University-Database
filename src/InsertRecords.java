import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertRecords {
    public static void main(String[] args) {
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

            String insertIntoSTUDENT = "INSERT INTO STUDENT(Name, Student_number, Class, Major)" +
                    "VALUES" +
                    "('Smith', '17', '1', 'CS')," +
                    "('Brown', '8', '2', 'CS');";
            commands.add(insertIntoSTUDENT);

            String insertIntoCOURSE = "INSERT INTO COURSE(Course_name, Course_number, Credit_hours, Department)" +
                    "VALUES" +
                    "('Intro to Computer Science','CS1310', 4, 'CS')," +
                    "('Data Structures','CS3320', 4, 'CS')," +
                    "('Discrete Mathematics','MATH2410', 3, 'MATH')," +
                    "('Database','CS3380', 3, 'CS');";
            commands.add(insertIntoCOURSE);

            String insertIntoSECTION = "INSERT INTO SECTION(Section_identifier, Course_number, Semester, Year, Instructor)" +
                    "VALUES" +
                    "(  85, 'MATH2410', 'Fall', '07', 'King')," +
                    "(  92, 'CS1310', 'Fall', '07', 'Anderson')," +
                    "( 102, 'CS3320', 'Spring', '08', 'Knuth')," +
                    "( 112, 'MATH2410', 'Fall', '08', 'Chang')," +
                    "( 119, 'CS1310', 'Fall', '08', 'Anderson')," +
                    "( 135, 'CS3380', 'Fall', '08', 'Stone');";
            commands.add(insertIntoSECTION);

            String insertIntoGRADEREPORT = "INSERT INTO GRADE_REPORT(Student_number, Section_identifier, Grade)" +
                    "VALUES" +
                    "('17',  112, 'B')," +
                    "('17',  119, 'C')," +
                    "('8',  85, 'A')," +
                    "('8',  92, 'A')," +
                    "('8',  102, 'B')," +
                    "('8',  135, 'A');";
            commands.add(insertIntoGRADEREPORT);

            String inertIntoPREREQUISITE = "INSERT INTO PREREQUISITE(Course_number, Prerequisite_number)" +
                    "VALUES" +
                    "('CS3380', 'CS3320')," +
                    "('CS3380', 'MATH2410')," +
                    "('CS3320', 'CS1310');";
            commands.add(inertIntoPREREQUISITE);

            for(String command : commands){stmt.execute(command);}

            connection.close();


        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
