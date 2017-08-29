package pl.gielerak.domain.repository.model;

import org.junit.Test;
import pl.gielerak.domain.owner.model.Owner;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.model.Name;

import static org.junit.Assert.*;

public class FullNameTest {
    @Test
    public void getFullName() throws Exception {
        String fullNameString = "Joe/repository";

        FullName fullName = get();

        assertEquals(fullNameString, fullName.getFullName());
        assertTrue(fullName.getFullName() instanceof String);
    }

    public static FullName get() {
        return new FullName(new Owner("Joe"), new Name("repository"));
    }

}