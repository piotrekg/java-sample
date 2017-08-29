package pl.gielerak.domain.repository.exception;

import pl.gielerak.domain.repository.model.FullName;

public class RepositoryNotFoundException extends Throwable {
    public RepositoryNotFoundException(FullName fullName) {
        super(String.format(
                "Repository \"%s\" not found!",
                fullName.getFullName()
        ));
    }
}
