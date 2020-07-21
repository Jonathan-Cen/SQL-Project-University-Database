import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        //Configure the logger
        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.OFF);

        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Section.class).addAnnotatedClass(GradeReport.class).addAnnotatedClass(Prerequisite.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Object> toStore = new ArrayList<>();
        //Add records for the STUDENT table
        toStore.add(new Student("Smith", "17", "1", "CS"));
        toStore.add(new Student("Brown", "8", "2", "CS"));
        //Add records to the COURSE table
        toStore.add(new Course("Intro to Computer Science", "CS1310", 4, "CS"));
        toStore.add(new Course("Data Structure", "CS3320", 4, "CS"));
        toStore.add(new Course("Discrete Mathematics", "MATH2410", 3, "MATH"));
        toStore.add(new Course("Database", "CS3380", 3, "CS"));
        //Add records to the SECTION table
        toStore.add(new Section(85, "MATH2410", "Fall", "07", "King"));
        toStore.add(new Section( 92, "CS1310", "Fall", "07", "Anderson"));
        toStore.add(new Section( 102, "CS3320", "Spring", "08", "Kunth"));
        toStore.add(new Section( 112, "MATH2410", "Fall", "08", "Chang"));
        toStore.add(new Section( 119, "CS1310", "Fall", "08", "Anderson"));
        toStore.add(new Section( 135, "CS3380", "Fall", "08", "Stone"));
        //Add records to the GRADE_REPORT table
        toStore.add(new GradeReport("17", 112 , "B"));
        toStore.add(new GradeReport("17",  119, "C"));
        toStore.add(new GradeReport("8",  85, "A"));
        toStore.add(new GradeReport("8",  92, "A"));
        toStore.add(new GradeReport("8", 102 , "B"));
        toStore.add(new GradeReport("8",  135, "A"));
        //Add records to the PREREQUISITE table
        toStore.add(new Prerequisite("CS3380","CS3320"));
        toStore.add(new Prerequisite("CS3380","MATH2410"));
        toStore.add(new Prerequisite("CS3320","CS1310"));






        //Saving all the records to database
        for(Object obj : toStore) {
            session = factory.getCurrentSession();
            try{
                session.beginTransaction();
                session.save(obj);
                session.getTransaction().commit();
                System.out.println(obj.toString() + " is saved.");
            }catch(Exception e){ //catch any Primary Key Constraint Violation.
                System.out.println("Saving failed. " + obj.toString() + " already exist.");
            }

        }




        session.close();
        factory.close();
    }

    public static void save(){

    }
}
