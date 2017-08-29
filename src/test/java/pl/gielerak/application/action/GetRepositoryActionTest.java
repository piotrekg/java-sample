package pl.gielerak.application.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.gielerak.application.action.GetRepositoryAction;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullNameTest;
import pl.gielerak.domain.repository.model.RepositoryResponse;
import pl.gielerak.domain.repository.model.RepositoryTest;
import pl.gielerak.infrastructure.repository.HttpRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GetRepositoryAction.class)
public class GetRepositoryActionTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpRepository repository;

    @Test
    public void getRepository() throws Exception, RepositoryNotFoundException {
        pl.gielerak.domain.repository.model.Repository repositoryMock = RepositoryTest.get();
        RepositoryResponse repositoryResponse = new RepositoryResponse(repositoryMock);

        when(repository.findByFullName(FullNameTest.get())).thenReturn(repositoryMock);

        mockMvc.perform(get("/repositories/{name}/{repositoryName}", "Joe", "repository")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value(repositoryResponse.getFullName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(repositoryResponse.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(repositoryResponse.getStars()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloneUrl").value(repositoryResponse.getCloneUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value(repositoryResponse.getCreatedAt()));

        verify(repository, times(1)).findByFullName(FullNameTest.get());
        verifyNoMoreInteractions(repository);
    }
}