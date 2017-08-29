package pl.gielerak.domain.repository.model;

import org.junit.Test;
import pl.gielerak.domain.date.model.DateFormat;
import pl.gielerak.domain.repository.model.Repository;
import pl.gielerak.domain.repository.model.RepositoryResponse;

import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RepositoryResponseTest {
    @Test
    public void getters() {
        Repository repository = RepositoryTest.get();
        RepositoryResponse repositoryResponse = new RepositoryResponse(repository);

        assertEquals(repository.getFullName().toString(), repositoryResponse.getFullName().toString());
        assertThat(repository.getDescription(), samePropertyValuesAs(repositoryResponse.getDescription()));
        assertThat(repository.getStars(), samePropertyValuesAs(repositoryResponse.getStars()));
        assertThat(repository.getCloneUrl(), samePropertyValuesAs(repositoryResponse.getCloneUrl()));
        assertEquals(
                repository.getCreatedAt().format(DateTimeFormatter.ofPattern(DateFormat.ISO8601.format())).toString(),
                repositoryResponse.getCreatedAt().toString()
        );
    }
}