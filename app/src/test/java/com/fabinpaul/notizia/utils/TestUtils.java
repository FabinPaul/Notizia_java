package com.fabinpaul.notizia.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.mock;

public class TestUtils {

    private TestUtils() {
    }

    public static String getStringFromFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        String line = "";
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static JSONObject getJSONObjectFromPath(String path) throws IOException, JSONException {
        return new JSONObject(getStringFromFile(path));
    }

    public static OkHttpClient getOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public static <T> List<T> createListOfMockResponses(Class<T> object, int count) {
        List<T> response = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            response.add(mock(object));
        }
        return response;
    }
}
