package com.fabinpaul.notizia.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class StubbedInterceptor implements Interceptor {

    private String mRequestMethod;
    private String mURL;
    private String mBody;

    public StubbedInterceptor(String jsonPath) throws URISyntaxException, IOException {
        this.mBody = TestUtils.getStringFromFile(Objects.requireNonNull(getClass().getResource(jsonPath)).toURI().getPath());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        mRequestMethod = request.method();
        mURL = request.url().toString();
        String body = mBody;
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

    public void setBody(@Nullable String mBody) {
        this.mBody = mBody;
    }
}
