import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost;integratedSecurity=true";

        try {
            //load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
            //Establish a connection
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Database connected\n");

            Statement stmt = connection.createStatement();
            String createUniversityDatabase = "CREATE DATABASE UNIVERSITY";
            stmt.execute(createUniversityDatabase);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
