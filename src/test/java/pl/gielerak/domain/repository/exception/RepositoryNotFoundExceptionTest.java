package pl.gielerak.domain.repository.exception;

import org.junit.Test;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.model.FullNameTest;

import static org.junit.Assert.*;

public class RepositoryNotFoundExceptionTest {
    @Test
    public void exception() {
        FullName fullName = FullNameTest.get();

        try {
            throw new RepositoryNotFoundException(fullName);
        } catch (RepositoryNotFoundException e) {
            assertEquals(new String("Repository \"Joe/repository\" not found!"), e.getMessage());
        }
    }
}