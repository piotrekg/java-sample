package pl.gielerak.infrastructure.client;

import okhttp3.*;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import pl.gielerak.infrastructure.client.Http;
import pl.gielerak.infrastructure.client.exception.NotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpTest {
    @Test
    public void callJson() throws Exception, NotFoundException {
        String responseString = new String("{\"testKey\":\"testValue\"}");
        String url = "http://localhost/";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message(responseString)
                .body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), responseString))
                .code(HttpStatus.OK.value())
                .build();

        Call call = mock(Call.class);
        when(call.execute()).thenReturn(response);

        OkHttpClient okHttpClient = mock(OkHttpClient.class);
        when(okHttpClient.newCall(request)).thenReturn(call);

        Http client = new Http(okHttpClient);
        JSONObject responseJson = client.callJson(request);

        assertEquals(new JSONObject(responseString).toString(), responseJson.toString());
    }
}