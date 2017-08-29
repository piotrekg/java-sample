package pl.gielerak.domain.repository.model;

import pl.gielerak.domain.date.model.DateFormat;

import java.time.format.DateTimeFormatter;

public class RepositoryResponse {
    private final Repository repository;

    public RepositoryResponse(Repository repository) {
        this.repository = repository;
    }

    public String getFullName() {
        return repository.getFullName().toString();
    }

    public String getDescription() {
        return repository.getDescription();
    }

    public String getCloneUrl() {
        return repository.getCloneUrl();
    }

    public int getStars() {
        return repository.getStars();
    }

    public String getCreatedAt() {
        return repository
                .getCreatedAt()
                .format(DateTimeFormatter.ofPattern(DateFormat.ISO8601.format()));
    }
}

