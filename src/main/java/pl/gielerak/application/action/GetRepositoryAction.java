package pl.gielerak.application.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.gielerak.domain.owner.model.Owner;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.model.Name;
import pl.gielerak.domain.repository.model.RepositoryResponse;
import pl.gielerak.domain.repository.repository.Repository;

@RestController
public class GetRepositoryAction {
    @Autowired
    private Repository repository;

    @RequestMapping("/repositories/{owner:[\\w-]+}/{repositoryName:[\\w-]+}")
    @ResponseBody
    public RepositoryResponse getRepository(
            @PathVariable("owner") String owner,
            @PathVariable("repositoryName") String repositoryName
    ) throws RepositoryNotFoundException {
        return new RepositoryResponse(
                repository.findByFullName(
                        new FullName(new Owner(owner), new Name(repositoryName))
                )
        );
    }
}
