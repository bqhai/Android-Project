package com.example.tudienanhviet;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tudienanhviet.SearchView.DBHelper;
import com.example.tudienanhviet.SearchView.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {
    static String filename = "data.txt";
    List<Word> wordList= new ArrayList<>();
    WordService wordService;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        wordService=APIUtils.getWordService();
        db= new DBHelper(WelcomeActivity.this);
        getWords();
        Utils.getYourWords.addAll(db.getYourWords());
        Utils.getHistory.addAll(db.getHistories());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(loadInternalFile(WelcomeActivity.this).size()==0) {
//                    writeToInternalFile(WelcomeActivity.this,wordList);
//                    Log.i("file1",loadInternalFile(WelcomeActivity.this).size()+"");
//                }
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

    public  void getWords(){
        Call<List<Word>> call = wordService.getWords();
        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                if(response.isSuccessful()){
                    Utils.list = response.body();
                    Log.i("testav",Utils.list.size()+"");
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
//    public void fillSuggest(List<Word> ls){
//        for (Word w:ls
//        ) {
//            list.add(new WordSuggestion(w.getMatu(),w.getWord(),w.getDetail()));
//            Utils.list.add(w);
//        }
//        Log.i("list",Utils.list.size()+"");
//    }
//    public static void writeToInternalFile(Context context, List<Word> arrayList) {
//        try {
//            File file = new File(context.getFilesDir(), filename);
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(arrayList);
//            oos.close();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<Word> loadInternalFile(Context context) {
//        try {
//            List<Word> arrayList = new ArrayList<>();
//            File file = new File(context.getFilesDir(), filename);
//            FileInputStream fis = new FileInputStream(file);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            arrayList = (List<Word>) ois.readObject();
//            Log.i("log1", arrayList.size() + "");
//            return arrayList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }
}