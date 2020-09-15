package com.example.tudienanhviet.SearchView;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import com.example.tudienanhviet.APIUtils;
import com.example.tudienanhviet.Word;
import com.example.tudienanhviet.WordService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class WordDataHelper  {
    public  static WordService wordService = APIUtils.getWordService();
    public static List<Word> list= new ArrayList<>();

    public  WordDataHelper(List<WordSuggestion> lst){

    }

        public static final String WORD_FILE_NAME = "words.json";

      //  public static List<WordWrapper> wordWrappers = new ArrayList<>();

        public static List<WordSuggestion> wordSuggestions;
//                new ArrayList<>(Arrays.asList(
//                new WordSuggestion("green"),
//                new WordSuggestion("blue"),
//                new WordSuggestion("pink"),
//                new WordSuggestion("purple"),
//                new WordSuggestion("brown")));



    public interface OnFindWordsListener {
        void onResults(List<Word> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<WordSuggestion> results);
    }

    public static List<WordSuggestion> getHistory(Context context, int count,List<WordSuggestion> lst) {

        List<WordSuggestion> suggestionList = new ArrayList<>();
        WordSuggestion wordSuggestion;
        for (int i = 0; i < lst.size(); i++) {
         //   wordSuggestion = Utils.fillSuggestion().get(i);
            wordSuggestion= lst.get(i);
            wordSuggestion.setIsHistory(true);
            suggestionList.add(wordSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory(List<WordSuggestion> lst) {
        for (WordSuggestion wordSuggestion :lst) {
            wordSuggestion.setIsHistory(false);
        }
//        for (WordSuggestion wordSuggestion :Utils.getHashMap().values()) {
//            wordSuggestion.setIsHistory(false);
//        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay, final List<WordSuggestion> lst,
                                       final WordDataHelper.OnFindSuggestionsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                WordDataHelper.resetSuggestionsHistory(lst);
                List<WordSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                   for (WordSuggestion  suggestion: lst) {
                   // for (WordSuggestion  suggestion: Utils.getHashMap().values()) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }

//                    for (WordSuggestion  suggestion: Utils.getHashMap().values()) {
//                        if (suggestion.getBody().toUpperCase()
//                                .startsWith(constraint.toString().toUpperCase())) {
//
//                            suggestionList.add(suggestion);
//                            if (limit != -1 && suggestionList.size() == limit) {
//                                break;
//                            }
//                        }
//                    }

                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<WordSuggestion>() {
                    @Override
                    public int compare(WordSuggestion lhs, WordSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<WordSuggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public static void findWords(Context context, String query, final List<Word> lst, final WordDataHelper.OnFindWordsListener listener) {
       // initWordWrapperList(context);
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Word> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Word word : lst) {
                        if (word.getWord().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(word);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<Word>) results.values);
                }
            }
        }.filter(query);

    }

//    private static void initWordWrapperList(Context context) {
//
//        if (Utils.getListWords().isEmpty()) {
//            Utils.getWords();
//        }
//    }

//    public static String loadJson(Context context) {
//
//        String jsonString;
//
//        try {
//            InputStream is = context.getAssets().open(WORD_FILE_NAME);
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            jsonString = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//        return jsonString;
//    }

//    private static List<WordWrapper> deserializeWord(String jsonString) {
//
//        Gson gson = new Gson();
//
//        Type collectionType = new TypeToken<List<WordWrapper>>() {
//        }.getType();
//        return gson.fromJson(jsonString, collectionType);
//    }
//public static void getWords(){
//    Call<List<Word>> call = wordService.getWords();
//    call.enqueue(new Callback<List<Word>>() {
//        @Override
//        public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
//            if(response.isSuccessful()){
//                list = response.body();
//                Log.i("testav",list.size()+"");
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Word>> call, Throwable t) {
//            Log.e("ERROR: ", t.getMessage());
//        }
//    });
//}
//    public static List<WordSuggestion> fillSuggestion(){
//        getWords();
//        List<WordSuggestion> ls= new ArrayList<>();
//        for (Word w:list
//        ) {
//            ls.add(new WordSuggestion(w.getWord()));
//        }
//        return  ls;
//    }

}
