package pl.gielerak.domain.owner.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.gielerak.domain.owner.model.Owner;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class OwnerTest {
    @Test
    public void getName() throws Exception {
        String name = "Joe";

        Owner owner = new Owner(name);

        assertEquals(name, owner.getName());
        assertTrue(owner.getName() instanceof String);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidOwner() {
        new Owner("test-username^^");
    }

    public static Owner get() {
        return new Owner("Joe");
    }

}
