package com.client;

import com.client.api.RequestFactory;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestFactoryImpl implements RequestFactory {

    private String URL;

    public RequestFactoryImpl(String URL) {
        this.URL = URL;
    }

    @Override
    public Request prepareGetRequest(Integer id) {
        Request request = new Request.Builder()
                .url(this.URL + "/getAmount/" + id)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return request;
    }

    @Override
    public Request prepareAddRequest(Integer id) {
        RequestBody requestBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(URL + "/addAmount/" + id + "?value=10")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        return request;
    }
}
