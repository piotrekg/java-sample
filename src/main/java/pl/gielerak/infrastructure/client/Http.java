package pl.gielerak.infrastructure.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.gielerak.infrastructure.client.exception.NotFoundException;

import java.io.IOException;

@Service
public class Http {
    private OkHttpClient client;
    private static final Logger logger = LoggerFactory.getLogger(Http.class);

    public Http() {
        this.client = new OkHttpClient();
    }

    public Http(OkHttpClient client) {
        this.client = client;
    }

    public JSONObject callJson(Request request) throws NotFoundException {
        logger.info("Make request to resource: "+ request.url());

        try (Response response = client.newCall(request).execute()) {
            logger.info("Response code: "+ response.code());

            if (HttpStatus.OK.value() == response.code()) {
                String body = response.body().string();
                logger.info("Response body: "+ body);

                return new JSONObject(body);
            }

        } catch (IOException e) {
            logger.error("IOException Caught: ", e);
        }

        throw new NotFoundException(request.url().toString());
    }
}
