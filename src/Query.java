import java.sql.*;

public class Query {

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

            /**Set the default value of the attribute 'DEPARTMENT' in the COURSE table to 'CS'*/
            String setDefault = "IF NOT(EXISTS(SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'COURSE' AND  " +
                    "COLUMN_NAME = 'Department' AND COLUMN_DEFAULT IS NOT NULL)) " +
                    "ALTER TABLE COURSE ADD CONSTRAINT DF_CS DEFAULT 'CS' FOR Department";
            stmt.execute(setDefault);
            System.out.println("The default value of the attribute Department has been set to 'CS' \n");

            /**Add a new attribute 'Birth_Date' to the STUDENT table with the 'DATE' type as its domain*/
            String addAttribute = "IF NOT(EXISTS(SELECT TOP 1 * FROM INFORMATION_SCHEMA.COLUMNS WHERE [TABLE_NAME] = 'STUDENT'AND [COLUMN_NAME] = 'Birth_Date'))" +
                    "ALTER TABLE STUDENT ADD Birth_Date DATE;";
            stmt.execute(addAttribute);
            System.out.println("The attribute 'Birth_Date' is added to STUDENT\n");

            /**Change the grade value of the student number '17' with the section identifier number 119 in the 'GRADE REPORT'
             table to B*/
            String changeGrade = "UPDATE GRADE_REPORT " +
                    "SET GRADE_REPORT.Grade = 'B' " +
                    "WHERE GRADE_REPORT.Student_number = '17' AND GRADE_REPORT.Section_identifier = 119;";
            stmt.execute(changeGrade);


            /**Define a query to retrieve the transcript records of the student number '8'. Transcript includes the
             * course name, course number, credit hours, semester, year, and grade for each course completed by the student*/
            String getTranscript = "SELECT COURSE.Course_name, COURSE.Course_number, COURSE.Credit_hours, SECTION.Semester, SECTION.Year, GRADE_REPORT.Grade " +
                    "FROM COURSE, SECTION, GRADE_REPORT " +
                    "WHERE GRADE_REPORT.Student_number = '8' AND GRADE_REPORT.Section_identifier = SECTION.Section_identifier AND " +
                    "SECTION.Course_number = COURSE.Course_number";
            System.out.println("****  Transcript of Student '8':   ****");
            ResultSet transcript = stmt.executeQuery(getTranscript);
                /**Obtain the metadata associated with transcript*/
            ResultSetMetaData metaData_transcript = transcript.getMetaData();
                /**Print column names*/

            printQueryResults(transcript, metaData_transcript);

            /**Define a query that retrieve the following: for each section taught by Professor 'Anderson', retrieve the course name,
             * course number, semester, year, and number of students who took the section*/

                /**Step 1: create a view of the cross join of the COURSE table, the SECTION table, and the GRADE_REPORT table to obtain full course details*/
            String createFullCourseDetails = "CREATE VIEW fullCourseDetails " +
                    "AS SELECT SECTION.Section_identifier,SECTION.Course_number, SECTION.Semester, SECTION.Year, SECTION.Instructor, COURSE.Course_name, " +
                    "COURSE.Credit_hours, COURSE.Department, GRADE_REPORT.Student_number, GRADE_REPORT.Grade " +
                    "FROM SECTION CROSS JOIN COURSE CROSS JOIN GRADE_REPORT " +
                    "WHERE SECTION.Course_number = COURSE.Course_number AND GRADE_REPORT.Section_identifier = SECTION.Section_identifier";

            String checkIfFullCourseDetailsExists = "SELECT 1 FROM sys.views WHERE Name='fullCourseDetails' ";
            ResultSet CheckIfFullCourseDetailsExists = stmt.executeQuery(checkIfFullCourseDetailsExists);
            if(!CheckIfFullCourseDetailsExists.next()){
                stmt.execute(createFullCourseDetails);
            }

                /**Step 2 retrieve the required data from the fullCourseDetails view*/
            String taughtByAnderson = "SELECT MAX(Course_name) AS 'Course Name', MAX(Course_number) AS 'Course Number', " +
                    "MAX(Semester) AS 'Semester', MAX(YEAR) AS 'Year', count(Section_identifier) AS '#Students' " +
                    "FROM fullCourseDetails " +
                    "WHERE Instructor = 'Anderson' " +
                    "GROUP BY Section_identifier";
            ResultSet TaughtByAnderson = stmt.executeQuery(taughtByAnderson);
            ResultSetMetaData metaData_Anderson = TaughtByAnderson.getMetaData();
            System.out.println("****  Taught by Anderson:   ****");
            printQueryResults(TaughtByAnderson, metaData_Anderson);



            /**Define a query to retrieve the names and major departments of students who did not study all
             * the courses that were offered in 2008*/

                /**Step 1 create a view that shows the course and student information in 2008*/
            String createStudentInfo2008 = "CREATE VIEW StudentInfo2008 " +
                    "AS SELECT SECTION.Section_identifier, SECTION.Course_number, SECTION.Semester, SECTION.Year, SECTION.Instructor, " +
                    "GRADE_REPORT.Student_number, GRADE_REPORT.Grade, STUDENT.Name, STUDENT.Major " +
                    "FROM SECTION CROSS JOIN GRADE_REPORT CROSS JOIN STUDENT " +
                    "WHERE SECTION.Section_identifier = GRADE_REPORT.Section_identifier AND SECTION.Year = '08' " +
                    "AND STUDENT.Student_number = GRADE_REPORT.Student_number";

            String checkIfStudentInfo2008Exists = "SELECT 1 FROM sys.views WHERE Name='StudentInfo2008' ";
            ResultSet CheckIfStudentInfo2008Exists = stmt.executeQuery(checkIfStudentInfo2008Exists);
            if(!CheckIfStudentInfo2008Exists.next()){
                stmt.execute(createStudentInfo2008);
            }
                /**Step 2 retrieve the total number of courses that were offered in 2008*/
            String getNumCoursesIn2008 = "SELECT COUNT(Section_identifier) AS 'target' FROM SECTION WHERE Year='08'";
            ResultSet course08 = stmt.executeQuery(getNumCoursesIn2008);
            course08.next();
            int target = course08.getInt("target");
                /**Step 3 create a view to find out how many courses each student took in 2008*/
            String createEnrollment2008 = "CREATE VIEW Enrollment2008 " +
                    "AS SELECT MAX(Name) AS 'Name', MAX(Major) AS 'Major', COUNT(Section_identifier)AS 'Courses_taken' " +
                    "FROM StudentInfo2008 " +
                    "GROUP BY Student_number";
            String checkIfEnrollment2008Exists = "SELECT 1 FROM sys.views WHERE Name='Enrollment2008' ";
            ResultSet CheckIfEnrollment2008Exists = stmt.executeQuery(checkIfEnrollment2008Exists);
            if(!CheckIfEnrollment2008Exists.next()){
                stmt.execute(createEnrollment2008);
            }
            /**Step 4 get the student name and major if they did not take all the courses that were offered in 2008*/
            String getStudents = "SELECT Name, Major FROM Enrollment2008 WHERE Courses_taken < " + target;
            ResultSet lessThanAllCourses = stmt.executeQuery(getStudents);
            ResultSetMetaData metaData_Students = lessThanAllCourses.getMetaData();
            System.out.println("****  Students who did not study all courses that were offered in 2008:   ****");
            printQueryResults(lessThanAllCourses, metaData_Students);

            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void printQueryResults(ResultSet result, ResultSetMetaData metaData) throws SQLException {
        for(int i = 1; i<= metaData.getColumnCount(); i++){
            System.out.print(metaData.getColumnLabel(i) + '\t');
            if(i==1) System.out.print('\t');
        }
        System.out.println();

        while (result.next()){
            for(int i = 1; i<= metaData.getColumnCount(); i++){
                if(i>1) System.out.print('\t');
                System.out.print(result.getString(i) + '\t');
            }
            System.out.println();
        }
        System.out.println();
    }
}
