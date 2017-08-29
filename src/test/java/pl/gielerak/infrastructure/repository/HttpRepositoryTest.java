package pl.gielerak.infrastructure.repository;

import org.junit.Test;
import org.springframework.core.env.Environment;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.model.FullNameTest;
import pl.gielerak.domain.repository.model.RepositoryTest;
import pl.gielerak.infrastructure.client.Http;
import pl.gielerak.infrastructure.client.exception.NotFoundException;
import pl.gielerak.infrastructure.repository.HttpRepository;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpRepositoryTest {
    @Test
    public void findByFullname() throws RepositoryNotFoundException, NotFoundException {
        FullName fullName = FullNameTest.get();
        pl.gielerak.domain.repository.model.Repository repositoryMock = RepositoryTest.get();

        Http client = mock(Http.class);
        when(client.callJson(anyObject())).thenReturn(RepositoryTest.getJsonObject());

        Environment env = mock(Environment.class);

        Repository repository = new HttpRepository(client, env);

        pl.gielerak.domain.repository.model.Repository repositoryResult = repository.findByFullName(fullName);

        assertThat(repositoryResult.getFullName(), samePropertyValuesAs(repositoryMock.getFullName()));
        assertThat(repositoryResult.getDescription(), samePropertyValuesAs(repositoryMock.getDescription()));
        assertThat(repositoryResult.getStars(), samePropertyValuesAs(repositoryMock.getStars()));
        assertThat(repositoryResult.getCloneUrl(), samePropertyValuesAs(repositoryMock.getCloneUrl()));
        assertThat(repositoryResult.getCreatedAt(), samePropertyValuesAs(repositoryMock.getCreatedAt()));
    }

}