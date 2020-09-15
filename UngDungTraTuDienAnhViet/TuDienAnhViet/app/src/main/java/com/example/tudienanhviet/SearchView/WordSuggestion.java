package com.example.tudienanhviet.SearchView;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

class WordSuggestion implements SearchSuggestion {
    private int matu;
    private String word;
    private String detail;
    private boolean mIsHistory = false;

    public WordSuggestion(int matu,String suggestion,String detail) {
        this.matu=matu;
        this.word = suggestion.toLowerCase();
        this.detail=detail;
    }

    public WordSuggestion(Parcel source) {
        this.word = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }
    public String getDetail() {
        return this.detail;
    }
    public int getMatu() {
        return this.matu;
    }


    @Override
    public String getBody() {
        return word;
    }

    public static final Creator<WordSuggestion> CREATOR = new Creator<WordSuggestion>() {
        @Override
        public WordSuggestion createFromParcel(Parcel in) {
            return new WordSuggestion(in);
        }

        @Override
        public WordSuggestion[] newArray(int size) {
            return new WordSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
