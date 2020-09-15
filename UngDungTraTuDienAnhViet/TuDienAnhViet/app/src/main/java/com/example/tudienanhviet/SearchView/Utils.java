package com.example.tudienanhviet.SearchView;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.tudienanhviet.APIUtils;
import com.example.tudienanhviet.R;
import com.example.tudienanhviet.Word;
import com.example.tudienanhviet.WordService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    static String filename = "yourword";
    public static List<Word> getHistory = new ArrayList<>();
    public static List<Word> getYourWords = new ArrayList<>();
//    public  static   WordService  wordService = APIUtils.getWordService();
    public static List<Word> list= new ArrayList<>();


    public static void writeToInternalFile(Context context,List<Word> arrayList) {
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Word> loadInternalFile(Context context) {
        try {
            List<Word> arrayList = new ArrayList<>();
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (List<Word>) ois.readObject();
            Log.i("log1", arrayList.size() + "");
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
//    public static Map<String,WordSuggestion> getHashMap(){
//      //  getWords();
//        Map<String,WordSuggestion> hash=  new HashMap<>();
//        for(WordSuggestion w:fillSuggestion()){
//            hash.put(w.getBody(),w);
//        }
//        return hash;
//    }
}
