package com.example.tudienanhviet.SearchView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tudienanhviet.IrregularVerbs;
import com.example.tudienanhviet.SearchView.Utils;
import com.example.tudienanhviet.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBHelper {
    Context con;
    String dbName="TuDienDatabase";

    public DBHelper(Context cont){
        this.con=cont;
        createDB();
    }

    private SQLiteDatabase openDB(){
        return con.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);

    }
    private void closeDB(SQLiteDatabase db){
        db.close();
    }

    private void createDB(){
        SQLiteDatabase db =openDB();
        String sqlYourWords = "CREATE TABLE IF NOT EXISTS YourWords (" +
                " matu int NOT NULL PRIMARY KEY," +
                " Word TEXT," +
                " Detail TEXT)" ;
        String sqlHistory = "CREATE TABLE IF NOT EXISTS Histories (" +
                " matu int NOT NULL PRIMARY KEY," +
                " Word TEXT," +
                " Detail TEXT)" ;
        String sqlIrre = "CREATE TABLE IF NOT EXISTS IrregularVerbs (" +
                " v1 TEXT," +
                " v2 TEXT," +
                " v3 TEXT," +
                " Detail TEXT)" ;
        db.execSQL(sqlYourWords);
        db.execSQL(sqlHistory);
        db.execSQL(sqlIrre);
        db.close();
    }
    public List<Word> getYourWords(){
        SQLiteDatabase db = openDB();
        ArrayList<Word> arr = new ArrayList<>();
        String sql = "select * from YourWords";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String word = csr.getString(1);
                    String detail = csr.getString(2);
                    arr.add(new Word(id,word,detail));
                } while (csr.moveToNext());
            }
        }
        Log.d("cate",arr.size()+"");
        closeDB(db);
        return arr;
    }
    public List<Word> getHistories(){
        SQLiteDatabase db = openDB();
        ArrayList<Word> arr = new ArrayList<>();
        String sql = "select * from Histories";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String word = csr.getString(1);
                    String detail = csr.getString(2);
                    arr.add(new Word(id,word,detail));
                } while (csr.moveToNext());
            }
        }
        Log.d("cate",arr.size()+"");
        closeDB(db);
        return arr;
    }
    public List<IrregularVerbs> getIrregularVerbs(){
        SQLiteDatabase db = openDB();
        ArrayList<IrregularVerbs> arr = new ArrayList<>();
        String sql = "select * from IrregularVerbs";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    String v1 = csr.getString(0);
                    String v2 = csr.getString(1);
                    String v3 = csr.getString(2);
                    String detail = csr.getString(3);
                    arr.add(new IrregularVerbs(v1,v2,v3,detail));
                } while (csr.moveToNext());
            }
        }
        Log.d("cate",arr.size()+"");
        closeDB(db);
        return arr;
    }

//    public ArList<Furniture> findByCatetgoriesID(int id){
//        ArrayList<Furniture> arr = new ArrayList<>();
//        for(Furniture f :getALLFurniture()){
//            if(f.getCategoriesID() == id){
//                arr.add(f);
//            }
//        }
//        return arr;
//    }


    public void insertYourWords(){
        removeYourWords();
        createDB();
        List<Word> arr = Utils.getYourWords;
        SQLiteDatabase db = openDB();
        for(Word w : arr) {
            if (checkKhoa(w.getMatu())) {
                ContentValues cv = new ContentValues();
                cv.put("matu", w.getMatu());
                cv.put("Word", w.getWord());
                cv.put("Detail", w.getDetail());
                db.insert("YourWords", null, cv);
            }
        }
        closeDB(db);
    }
    public void insertHistories(){
        removeHistories();
        createDB();
        List<Word> arr = Utils.getHistory;
        Log.d("size",arr.size()+"");
        SQLiteDatabase db = openDB();
        Random random = new Random();
        for(Word w : arr) {
            if(checkKhoaHistories(w.getMatu())) {
                ContentValues cv = new ContentValues();
                cv.put("matu", w.getMatu());
                cv.put("Word", w.getWord());
                cv.put("Detail", w.getDetail());
                db.insert("Histories", null, cv);
            }
        }
        closeDB(db);
    }
    public void insertIrregularVerbs(){
        removeHistories();
        createDB();
        List<IrregularVerbs> arr = getIrre();
        Log.d("size",arr.size()+"");
        SQLiteDatabase db = openDB();
        Random random = new Random();
        for(IrregularVerbs i : arr) {
                ContentValues cv = new ContentValues();
                cv.put("v1", i.getV1());
                cv.put("v2", i.getV2());
                cv.put("v3",i.getV3());
                cv.put("Detail", i.getDetail());
                db.insert("IrregularVerbs", null, cv);
        }
        closeDB(db);
    }
    public void removeYourWords()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = openDB(); // helper is object extends SQLiteOpenHelper
        db.delete("YourWords", null, null);

    }
    public void removeHistories(){
        SQLiteDatabase db = openDB(); // helper is object extends SQLiteOpenHelper
        db.delete("Histories", null, null);

    }

    public boolean checkKhoa(int ma){
        for (Word w:getYourWords()
             ) {
            if(w.getMatu()==ma){
                return  false;
            }
        }
        return  true;
    }
    public boolean checkKhoaHistories(int ma){
        for (Word w:getHistories()
        ) {
            if(w.getMatu()==ma){
                return  false;
            }
        }
        return  true;
    }

    public ArrayList<IrregularVerbs> getIrre(){
        ArrayList<IrregularVerbs> arrl= new ArrayList<>();
        arrl.add(new IrregularVerbs("abide","abode/abided","abode/abided", "lưu trú, lưu lại"));
        arrl.add(new IrregularVerbs("arise","arose","arisen", "phát sinh"));
        arrl.add(new IrregularVerbs("awake","awoke","awoken", "đánh thức, thức"));
        arrl.add(new IrregularVerbs("backslide","backslid","backslidden/backslid", "tái phạm"));
        arrl.add(new IrregularVerbs("be","was/were","been", "thì, là, bị, ở"));
        arrl.add(new IrregularVerbs("become", "became", "become", "trở nên"));
        arrl.add(new IrregularVerbs("begin", "began", "begun", "bắt đầu"));
        arrl.add(new IrregularVerbs("bend", "bent", "bent", "bẻ cong"));
        arrl.add(new IrregularVerbs("bet", "bet/betted", "bet/betted", "đánh cược, cá cược"));
        arrl.add(new IrregularVerbs("bind", "bound", "bound", "buộc, trói"));
        arrl.add(new IrregularVerbs("bite", "bit", "bitten", "cắn"));
        arrl.add(new IrregularVerbs("blow", "blew", "blown", "thổi"));
        arrl.add(new IrregularVerbs("break", "broke", "broken", "đập vỡ"));
        arrl.add(new IrregularVerbs("breed", "bred", "bred", "nuôi, dạy dỗ"));
        arrl.add(new IrregularVerbs("bring", "brought", "brought", "mang đến"));
        arrl.add(new IrregularVerbs("build", "built", "built", "xây dựng"));
        arrl.add(new IrregularVerbs("burn", "burnt/burned", "burnt/burned", "đốt, cháy"));
        arrl.add(new IrregularVerbs("buy", "bought", "bought", "mua"));
        arrl.add(new IrregularVerbs("catch", "caught", "caught", "bắt, chụp"));
        arrl.add(new IrregularVerbs("choose", "chose", "chosen", "chọn, lựa"));
        arrl.add(new IrregularVerbs("cleave", "clave", "claved", "dính chặt"));
        arrl.add(new IrregularVerbs("cling", "clung", "clung", "bám vào, dính chặt"));
        arrl.add(new IrregularVerbs("cost", "cost", "cost", "có giá là"));
        arrl.add(new IrregularVerbs("creep", "crept", "crept", "bò, trườn, lẻn"));
        arrl.add(new IrregularVerbs("crow", "crew/crewed", "crowed", "gáy (gà)"));
        arrl.add(new IrregularVerbs("cut", "cut", "cut", "cắt, chặt"));
        arrl.add(new IrregularVerbs("deal", "dealt", "dealt", "giao thiệp"));
        arrl.add(new IrregularVerbs("dig", "dug", "dug", "đào"));
        arrl.add(new IrregularVerbs("dive", "dove/dived", "dived", "lặn, lao xuống"));
        arrl.add(new IrregularVerbs("do", "did", "done", "làm"));
        arrl.add(new IrregularVerbs("draw", "drew", "drawn", "vẽ, kéo"));
        arrl.add(new IrregularVerbs("dream", "dreamt/dreamed", "dreamt/dreamed", "mơ thấy"));
        arrl.add(new IrregularVerbs("drink", "drank", "drunk", "uống"));
        arrl.add(new IrregularVerbs("drive", "drove", "driven", "lái xe"));
        arrl.add(new IrregularVerbs("dwell", "dwelt", "dwelt", "trú ngụ, ở"));
        arrl.add(new IrregularVerbs("eat", "ate", "eaten", "ăn"));
        arrl.add(new IrregularVerbs("fall", "fell", "fallen", "ngã rơi"));
        arrl.add(new IrregularVerbs("feed", "fed", "fed", "cho ăn, nuôi"));
        arrl.add(new IrregularVerbs("feel", "felt", "felt", "cảm thấy"));
        arrl.add(new IrregularVerbs("fight", "fought", "fought", "chiến đấu"));
        arrl.add(new IrregularVerbs("find", "found", "found", "tìm thấy, thấy"));
        arrl.add(new IrregularVerbs("fit", "fit/fitted", "fit/fitted", "làm cho vừa, làm cho hợp"));
        arrl.add(new IrregularVerbs("flee", "fled", "fled", "chạy trốn"));
        arrl.add(new IrregularVerbs("fling", "flung", "flung", "tung, quăng"));
        arrl.add(new IrregularVerbs("fly", "flew", "flown", "bay"));
        arrl.add(new IrregularVerbs("forbid", "forbase/forbad", "forbidden", "cấm, cấm đoán"));
        arrl.add(new IrregularVerbs("forego", "forewent", "foregone", "bỏ, kiêng"));
        arrl.add(new IrregularVerbs("forget", "forgot", "forgotten", "quên"));
        arrl.add(new IrregularVerbs("forgive", "forgave", "forgiven", "tha thứs"));

        return  arrl;
    }

}
