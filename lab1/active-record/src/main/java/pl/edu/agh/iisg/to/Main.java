package pl.edu.agh.iisg.to;

import pl.edu.agh.iisg.to.connection.ConnectionProvider;
import pl.edu.agh.iisg.to.executor.QueryExecutor;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

public class Main {

    public static void main(String[] args) {
    	//This time we execute only junit tests
        var connection = ConnectionProvider.getConnection();
        var course = Course.create("course");
        var student = Student.create("joo", "cioe", 124);
        student.ifPresent(value -> {
            System.out.println(value);
            course.get().enrollStudent(value);
        });

        System.out.println(course.get().studentList());
    }
}
