# SQL_Project-University_Database
A SQL project that uses JDBC and Microsoft SQL Server

### UNIVERSITY database schema:

![Alt text](/screenshots/Database_Schema.png?raw=true "Database Schema")

### UNIVERSITY database state:

![Alt text](/screenshots/Database_States.png?raw=true "Database States")

### Java Programs (Please run these programs in order):
- ```CreateDatabase.java``` creates the UNIVERSITY database on the local Microsoft SQL server
- ```CreateTables.java``` creates tables and specifies all primary keys and foreign keys as per the database schema
- ```InsertRecords.java``` inserts records to the UNIVERSITY database as per the database state
- ```Query.java``` execute the following queries and output the results console where appropriate:
  - **Set the default value of the attribute 'Department' in the COURSE table to 'CS'.**
  - **Add a new attribute 'Birth_Date' to the STUDENT table with the 'DATE' type as its domain.**
  - **Change the grade value of the student number '17' with the section number 119 in the 'GRADE_REPORT' table to 'B'.**
  - **Define a query to retrieve the transcript records of the student number '8'. Transcript includes the course name, course number, credit hours, semester, year, and grade for each course completed by the student.**
  - **For each section taught by Professor 'Anderson', retrieve the course name, course number, semester, year, and number of students who took the section.**
  - **Retrieve the names and major departments of the students who did not study all the courses that were offered in 2008.**

### Microsoft SQL Server setup for this program:
1. Download the [Microsoft JDBC driver](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15) 
2. Place the ```mssql-jdbc-8.2.2.jre8.jar``` in ```C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext``` 
3. Find the correct ```dll``` file from ```sqljdbc_8.2.2.0_chs\sqljdbc_8.2\chs\auth``` and place it in ```C:\Windows\System32```

### Sample output of ```Query.java```

![Alt text](/screenshots/Sample_Outputs.png?raw=true "Sample Outputs")
