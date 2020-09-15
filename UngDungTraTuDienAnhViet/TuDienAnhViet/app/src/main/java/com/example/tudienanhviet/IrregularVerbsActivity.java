package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tudienanhviet.SearchView.DBHelper;
import com.example.tudienanhviet.SearchView.IrregularVerbAdapter;
import com.example.tudienanhviet.SearchView.Utils;
import com.example.tudienanhviet.SearchView.WordResultList;

public class IrregularVerbsActivity extends AppCompatActivity {
    IrregularVerbAdapter mIrregularVerbAdapter;
    RecyclerView mRecycle;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregular_verbs);
        getSupportActionBar().setTitle("Động từ bất quy tắc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db= new DBHelper(IrregularVerbsActivity.this);
        db.insertIrregularVerbs();
        mRecycle = (RecyclerView) findViewById(R.id.search_results_list_irregularVerds);
        setupResultsList();
    }
    private void setupResultsList() {
       mIrregularVerbAdapter = new IrregularVerbAdapter();
        mRecycle.setAdapter(mIrregularVerbAdapter);
        Log.i("his", Utils.getYourWords.size()+"");
        mIrregularVerbAdapter.swapData(db.getIrregularVerbs());
        mRecycle.setLayoutManager(new LinearLayoutManager(IrregularVerbsActivity.this));
    }
}