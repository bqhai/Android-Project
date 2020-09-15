package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VietAnhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viet_anh);
        getSupportActionBar().setTitle("Từ điển Việt Anh");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}