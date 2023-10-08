package pl.edu.agh.iisg.to.dao;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradeDao extends GenericDao<Grade> {

    public boolean gradeStudent(final Student student, final Course course, final float gradeValue) {
        //TODO implement
        var studentDao = new StudentDao();
        var courseDao = new CourseDao();
        try {
            if (studentDao.findByIndexNumber(student.indexNumber()).isPresent() &&
                    courseDao.findByName(course.name()).isPresent()) {
                var grade = new Grade(student, course, gradeValue);
                courseDao.enrollStudent(course, student);
                save(grade);

                course.gradeSet().add(grade);
                student.gradeSet().add(grade);

                studentDao.update(student);
                courseDao.update(course);
                return true;
            }
        } catch(PersistenceException e){
            e.printStackTrace();
        }
        return false;
    }


}
