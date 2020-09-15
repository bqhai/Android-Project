package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tudienanhviet.SearchView.DBHelper;
import com.example.tudienanhviet.SearchView.Utils;
import com.example.tudienanhviet.SearchView.WordResultList;

public class YourWordActivity extends AppCompatActivity {
    private RecyclerView mSearchResultsList;
    private WordResultList mSearchResultsAdapter;
    Button btnXoa;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DBHelper(YourWordActivity.this);
        db.insertYourWords();
        setContentView(R.layout.activity_your_word);
        getSupportActionBar().setTitle("Từ của bạn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSearchResultsList = (RecyclerView) findViewById(R.id.search_results_list_yourwords);
        setupResultsList();
        btnXoa= findViewById(R.id.btnXoaYourWord);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getYourWords.clear();
                db.insertYourWords();
                setupResultsList();
            }
        });
    }

    private void setupResultsList() {
        mSearchResultsAdapter = new WordResultList();
        mSearchResultsList.setAdapter(mSearchResultsAdapter);
        Log.i("his", Utils.getYourWords.size()+"");
        mSearchResultsAdapter.swapData(db.getYourWords());
        mSearchResultsList.setLayoutManager(new LinearLayoutManager(YourWordActivity.this));
    }
}