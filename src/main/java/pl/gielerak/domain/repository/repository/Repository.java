package pl.gielerak.domain.repository.repository;

import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;

public interface Repository {
    pl.gielerak.domain.repository.model.Repository findByFullName(FullName fullName) throws RepositoryNotFoundException;
}
