package com.example.tudienanhviet;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://10.3.106.60:5000/api/";

    public static WordService getWordService(){
        return RetrofitClient.getClient(API_URL).create(WordService.class);
    }
}
