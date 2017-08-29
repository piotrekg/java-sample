package pl.gielerak.domain.repository.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.gielerak.domain.repository.model.Name;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class NameTest {
    @Test
    public void getName() throws Exception {
        String repositoryName = "repository";

        Name name = new Name(repositoryName);

        assertEquals(repositoryName, name.getName());
        assertTrue(name.getName() instanceof String);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidRepositoryName() {
        new Name("test-repository-name^^");
    }

    public static Name get() {
        return new Name("Joe");
    }

}
