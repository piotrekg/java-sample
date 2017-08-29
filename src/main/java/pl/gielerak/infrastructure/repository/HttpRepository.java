package pl.gielerak.infrastructure.repository;

import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.infrastructure.client.Http;
import pl.gielerak.infrastructure.client.exception.NotFoundException;

@Service
public class HttpRepository implements Repository {
    private final static String BASE_PATH = "https://api.github.com";

    final private Http client;
    final private Environment env;

    public HttpRepository(Http client, Environment env) {
        this.client = client;
        this.env = env;
    }

    @Override
    public pl.gielerak.domain.repository.model.Repository findByFullName(FullName fullName) throws RepositoryNotFoundException {
        String path = "/repos/"+fullName;
        JSONObject response;

        try {
            response = client.callJson(request(path));
        } catch (NotFoundException e) {
            throw new RepositoryNotFoundException(fullName);
        }

        return pl.gielerak.domain.repository.model.Repository.fromJsonObject(response);
    }

    private Request request(String path) {
        String url = BASE_PATH + path;
        String token = env.getProperty("github.oauth-token");

        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "token "+ token)
                .build();
    }
}
