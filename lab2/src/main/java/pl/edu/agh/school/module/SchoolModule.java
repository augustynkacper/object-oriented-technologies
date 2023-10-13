package pl.edu.agh.school.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SchoolModule extends AbstractModule {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ClassesStorageFile {}

    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("teachersStorageFile"))
                .toInstance("teachers.dat");
    }

    @Provides
    @ClassesStorageFile
    String provideClassesStorageFile() {
        return "classes.dat";
    }

    @Provides
    IPersistenceManager providePersistenceManager(
            SerializablePersistenceManager persistenceManager) {
        return persistenceManager;
    }
}
