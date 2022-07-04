package com.client.api;

import okhttp3.Request;

public interface RequestFactory {
    Request prepareGetRequest(Integer id);
    Request prepareAddRequest(Integer id);
}
