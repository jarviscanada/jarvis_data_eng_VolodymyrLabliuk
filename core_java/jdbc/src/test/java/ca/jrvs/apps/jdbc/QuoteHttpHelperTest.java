package ca.jrvs.apps.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuoteHttpHelperTest {
    QuoteHttpHelper quoteHttpHelper;

    OkHttpClient client;
    Response response;
    Call call;
    ResponseBody responseBody;

    @Before
    public void setup() throws IOException {
        responseBody = mock(ResponseBody.class);
        client = mock(OkHttpClient.class);
        response = Mockito.mock(Response.class);
        call = Mockito.mock(Call.class);
        quoteHttpHelper = new QuoteHttpHelper(client);
//        String json = //Sample Quote from Alpha Vantage
//                "{ \"Global Quote\": {" +
//                        "\"01. symbol\": \"MSFT\"," +
//                        "\"02. open\": \"332.3800\"," +
//                        "\"03. high\": \"333.8300\"," +
//                        "\"04. low\": \"326.3600\"," +
//                        "\"05. price\": \"327.7300\"," +
//                        "\"06. volume\": \"21085695\"," +
//                        "\"07. latest trading day\": \"2023-10-13\"," +
//                        "\"08. previous close\": \"331.1600\"," +
//                        "\"09. change\": \"-3.4300\"," +
//                        "\"10. change percent\": \"-1.0358%\"}}";

    }

    @Test
    public void checkQuoteJsonParsing(){

        String json = //Sample Quote from Alpha Vantage
                "{ \"Global Quote\": {" +
                        "\"01. symbol\": \"MSFT\"," +
                        "\"02. open\": \"332.3800\"," +
                        "\"03. high\": \"333.8300\"," +
                        "\"04. low\": \"326.3600\"," +
                        "\"05. price\": \"327.7300\"," +
                        "\"06. volume\": \"21085695\"," +
                        "\"07. latest trading day\": \"2023-10-13\"," +
                        "\"08. previous close\": \"331.1600\"," +
                        "\"09. change\": \"-3.4300\"," +
                        "\"10. change percent\": \"-1.0358%\"}}";
        try {
            ResponseBody responseBody = mock(ResponseBody.class);
            when(responseBody.string()).thenReturn(json);

            Response response = new Response.Builder()
                    .request(new Request.Builder().url("http://example.com").build())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .body(responseBody)
                    .build();
            when(call.execute()).thenReturn(response);
            when(client.newCall(Mockito.any(Request.class))).thenReturn(call);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->{quoteHttpHelper.fetchQuoteInfo("AAPL");});
    }
}

