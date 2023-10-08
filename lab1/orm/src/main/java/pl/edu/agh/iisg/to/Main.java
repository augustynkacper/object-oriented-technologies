package pl.edu.agh.iisg.to;

import pl.edu.agh.iisg.to.dao.StudentDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;
import pl.edu.agh.iisg.to.session.SessionService;

public class Main {

    public static void main(String[] args) {
        SessionService.openSession();
        var studentDao = new StudentDao();
        //var student = studentDao.create("asdf", "asdf", 12);
        SessionService.closeSession();
    }

}
