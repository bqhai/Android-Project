package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.tudienanhviet.SearchView.DBHelper;
import com.example.tudienanhviet.SearchView.Utils;
import com.example.tudienanhviet.SearchView.WordResultList;

public class TranslateHistoryActivity extends AppCompatActivity {
    private RecyclerView mSearchResultsList;
    private WordResultList mSearchResultsAdapter;
    Button btnXoa;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_history);
        db= new DBHelper(TranslateHistoryActivity.this);
        db.insertHistories();
        getSupportActionBar().setTitle("Từ đã tra");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSearchResultsList = (RecyclerView) findViewById(R.id.search_results_list_history);
        btnXoa=findViewById(R.id.btnXoaTuDaTra);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getHistory.clear();
                db.insertHistories();
                setupResultsList();
            }
        });
        setupResultsList();
    }
    private void setupResultsList() {
        mSearchResultsAdapter = new WordResultList();
        mSearchResultsList.setAdapter(mSearchResultsAdapter);
        Log.i("his",Utils.getHistory.size()+"");
        mSearchResultsAdapter.swapData(db.getHistories());
        mSearchResultsList.setLayoutManager(new LinearLayoutManager(TranslateHistoryActivity.this));
    }
}