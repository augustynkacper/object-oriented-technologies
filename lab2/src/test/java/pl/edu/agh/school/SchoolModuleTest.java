package pl.edu.agh.school;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.module.SchoolModule;

public class SchoolModuleTest {
    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new SchoolModule());
    }

    @Test
    public void testLoggerIsSingleton() {
        // when & then
        Logger logger1 = injector.getInstance(Logger.class);
        Logger logger2 = injector.getInstance(Logger.class);
        Assertions.assertEquals(logger1, logger2);
    }
}
