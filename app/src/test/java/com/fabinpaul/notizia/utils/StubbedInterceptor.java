package com.fabinpaul.notizia.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class StubbedInterceptor implements Interceptor {

    private String mRequestMethod;
    private String mURL;

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        mRequestMethod = request.method();
        mURL = request.url().toString();
        String body = request.body() == null ? "" : request.body().toString();
        return new Response.Builder()
                .code(200)
                .message(body)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), body.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }

    public String getRequestMethod() {
        return mRequestMethod;
    }

    public String getURL() {
        return mURL;
    }
}
