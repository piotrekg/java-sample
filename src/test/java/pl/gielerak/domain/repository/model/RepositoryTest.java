package pl.gielerak.domain.repository.model;

import org.json.JSONObject;
import org.junit.Test;
import pl.gielerak.domain.date.model.DateFormat;
import pl.gielerak.domain.owner.model.Owner;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.model.Name;
import pl.gielerak.domain.repository.model.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class RepositoryTest {
    @Test
    public void getters() throws Exception {
        Repository repository = get();

        assertEquals(new FullName(new Owner("Joe"), new Name("repository")), repository.getFullName());
        assertEquals(new String("test description"), repository.getDescription());
        assertEquals(new Integer(42), repository.getStars());
        assertEquals(new String("http://github.com/testowner/testrepo.git"), repository.getCloneUrl());
        assertEquals(LocalDateTime.parse(
                "2017-08-20T10:23:01Z",
                DateTimeFormatter.ofPattern(DateFormat.ISO8601.format())
        ), repository.getCreatedAt());
    }

    public static Repository get() {
        return new Repository(
                FullNameTest.get(),
                "test description",
                42,
                "http://github.com/testowner/testrepo.git",
                LocalDateTime.parse(
                        "2017-08-20T10:23:01Z",
                        DateTimeFormatter.ofPattern(DateFormat.ISO8601.format())
                )
        );
    }

    public static JSONObject getJsonObject() {
        return new JSONObject("{\"created_at\":\"2017-08-20T10:23:01Z\",\"description\":\"test description\",\"full_name\":\"Joe/repository\",\"stargazers_count\":42,\"clone_url\":\"http://github.com/testowner/testrepo.git\"}");
    }
}