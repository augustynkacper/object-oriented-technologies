package pl.edu.agh.school.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;
import pl.edu.agh.school.module.SchoolModule;

public final class SerializablePersistenceManager implements IPersistenceManager{

   // private static final Logger log = Logger.getInstance();
    private final Logger logger;

    private String teachersStorageFileName;

    private String classStorageFileName;

    @Inject
    public SerializablePersistenceManager(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void saveTeachers(List<Teacher> teachers) {
        if (teachers == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
            oos.writeObject(teachers);
            logger.log(String.format("teachers saved to %s", teachersStorageFileName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            logger.log("There was an error while saving the teachers data", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Teacher> loadTeachers() {
        ArrayList<Teacher> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {
            res = (ArrayList<Teacher>) ios.readObject();
            logger.log(String.format("teachers read from %s", teachersStorageFileName));
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            logger.log("There was an error while loading the teachers data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }

    @Override
    public void saveClasses(List<SchoolClass> classes) {
        if (classes == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {
            oos.writeObject(classes);
            logger.log(String.format("classes saved to %s", teachersStorageFileName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            logger.log("There was an error while saving the classes data", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SchoolClass> loadClasses() {
        ArrayList<SchoolClass> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
            res = (ArrayList<SchoolClass>) ios.readObject();
            logger.log(String.format("classes read from %s", teachersStorageFileName));
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            logger.log("There was an error while loading the classes data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }

    @Inject
    @Override
    public void setClassStorageFileName(@SchoolModule.ClassesStorageFile String fileName) {
        this.classStorageFileName = fileName;
    }

    @Inject
    @Override
    public void setTeachersStorageFileName(@Named("teachersStorageFile") String fileName) {
        this.teachersStorageFileName = fileName;
    }

}
