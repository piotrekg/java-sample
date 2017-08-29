package pl.gielerak.infrastructure.client.exception;

import org.junit.Test;
import pl.gielerak.infrastructure.client.exception.NotFoundException;

import static org.junit.Assert.*;

public class NotFoundExceptionTest {
    @Test
    public void exception() {
        String path = "http://localhost/";

        try {
            throw new NotFoundException(path);
        } catch (NotFoundException e) {
            assertEquals(new String("Resource \"http://localhost/\" not found!"), e.getMessage());
        }
    }
}