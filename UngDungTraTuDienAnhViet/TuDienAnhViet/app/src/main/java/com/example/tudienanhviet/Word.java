package com.example.tudienanhviet;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Word{
    @SerializedName("matu")
    @Expose
    private int matu;
    @SerializedName("word")
    @Expose
    private String word1;
    @SerializedName("detail")
    @Expose
    private String detail;

    public Word(){
    }
    public Word(int id,String word,String detail){
        this.matu=id;
        this.word1=word;
        this.detail=detail;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public int getMatu() {
        return matu;
    }

    public void setMatu(int matu) {
        this.matu = matu;
    }

    public String getWord() {
        return word1;
    }

    public void setWord(String word) {
        this.word1 = word;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
