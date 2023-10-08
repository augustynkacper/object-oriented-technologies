package pl.edu.agh.iisg.to.dao;

import java.util.*;
import java.util.stream.Collectors;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
    	try {
            save(new Student(firstName, lastName, indexNumber));
            return findByIndexNumber(indexNumber);
        } catch(PersistenceException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Student> findById(final int id) {
        try {
            Student student = currentSession()
                    .createQuery("SELECT s from Student s WHERE s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(student);
        } catch (PersistenceException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
        try {
            Student student = currentSession()
                    .createQuery("SELECT s from Student s WHERE s.indexNumber = :indexNumber", Student.class)
                    .setParameter("indexNumber", indexNumber)
                    .getSingleResult();
            return Optional.of(student);
        } catch (PersistenceException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student stdnt) {
        //TODO additional task
        try {
            var optionalStudent = findByIndexNumber(stdnt.indexNumber());
            if (optionalStudent.isPresent()) {
                return optionalStudent.get().courseSet().stream()
                        .filter(course -> !course.gradeSet().isEmpty())
                        .collect(Collectors.toMap(
                                course -> course,
                                course -> course.gradeSet().stream().map(Grade::grade).reduce(0f, Float::sum) / course.gradeSet().size()
                        ));
            }
        } catch (PersistenceException e){
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

}
