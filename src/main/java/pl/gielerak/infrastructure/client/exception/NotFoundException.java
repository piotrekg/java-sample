package pl.gielerak.infrastructure.client.exception;

public class NotFoundException extends Throwable {
    public NotFoundException(String resource) {
        super(String.format(
                "Resource \"%s\" not found!",
                resource
        ));
    }
}
