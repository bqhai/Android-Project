package com.example.tudienanhviet;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WordService {
    @GET("Vocabularies/{id}")
    Call<Word> getWord(@Path("id") String matu);
    @GET("Vocabularies/")
    Call<List<Word>> getWords();

    @POST("Vocabularies/")
    Call<Word> addWord(@Body Word word);

    @PUT("update/{id}")
    Call<Word> updateWord(@Path("id") int id, @Body Word word);

    @DELETE("delete/{id}")
    Call<Word> deleteWord(@Path("id") int id);
}
