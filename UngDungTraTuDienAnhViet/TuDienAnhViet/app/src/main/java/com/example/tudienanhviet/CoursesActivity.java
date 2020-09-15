package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    ListView lst;
    ArrayList<Course> arr= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        arr=addArray();
        getSupportActionBar().setTitle("Các khóa học");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lst=findViewById(R.id.listView_Courses);
        CoursesAdapter coursesAdapter= new CoursesAdapter(CoursesActivity.this,R.layout.list_courses,arr);
        lst.setAdapter(coursesAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(CoursesActivity.this,YouTubeActivity.class);
                i.putExtra("url",arr.get(position).getUrl());
                Log.i("ur",arr.get(position).getUrl());
                startActivity(i);
            }
        });
    }

    public ArrayList<Course> addArray(){
        ArrayList<Course> arrl= new ArrayList<>();
        arrl.add(new Course("Personal Development","7 Soft Skills You Should Not Learn Too Late In Life | Motivation | Personal Development","ErK1dLuTZMQ"));
        arrl.add(new Course("Clear English Grammar Doubts","Commonly Confused English Words - Each vs Every (Usage & Differences) Clear English Grammar Doubts","4ntf8jm1c1M"));
        arrl.add(new Course("15 Buzzwords You Must Use For In Your Answer","Job Interview Question - Tell Me About Yourself? - 15 Buzzwords You Must Use For In Your Answer","mCi5yzwMhuA"));
        arrl.add(new Course("Diplomatic Communication","Business English Phrases For Work Meetings | Learn English For Work | Diplomatic Communication","zY7mUJP28k8"));
        arrl.add(new Course("English Lesson","8 Advanced English Words To Replace ‘Walk’ | Speak English Fluently With Confidence | English Lesson","MAQs5OxqqSk"));
        arrl.add(new Course("Accent Reduction","How To Pronounce R Correctly? 99% Pronounce It Wrong | Speak English Like A Star! | Accent Reduction","yu3Q4Eh6r_0"));
        arrl.add(new Course("Learn English Online","English Medical Vocabulary - Important Medical Terms You Should Know | Learn English Online | Meera","EdrlVutgbXk"));
        arrl.add(new Course("Stop Translating In English","Stop Translating In English |10 Classic English Mistakes Made While Speaking English Via Translation","iumg-npFyXU"));
        arrl.add(new Course("Improve English pronunciation","Commonly Mispronounced English Words Ending in -ed (Past Verbs) | Improve English pronunciation","h8sonB4TgJM"));
        arrl.add(new Course("Prepositions","Prepositions - Next to, Near to, Close To, Besides | Common Grammar Mistakes English Learners Make","KEkoAIJGziE"));



        return  arrl;
    }
}