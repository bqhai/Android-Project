package com.example.tudienanhviet.SearchView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.example.tudienanhviet.APIUtils;
import com.example.tudienanhviet.R;
import com.example.tudienanhviet.Word;
import com.example.tudienanhviet.WordService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.arlib.floatingsearchviewdemo.R;
//import com.arlib.floatingsearchviewdemo.adapter.SearchResultsListAdapter;
//import com.arlib.floatingsearchviewdemo.data.ColorSuggestion;
//import com.arlib.floatingsearchviewdemo.data.ColorWrapper;
//import com.arlib.floatingsearchviewdemo.data.DataHelper;

public class SlidingSearchResultFragment extends BaseExampleFragment {
    private final String TAG = "BlankFragment";
//    WordService wordService;
//    List<Word> lst= new ArrayList<>();
    List<WordSuggestion> list= new ArrayList<WordSuggestion>();

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private FloatingSearchView mSearchView;

    private RecyclerView mSearchResultsList;
    private WordResultList mSearchResultsAdapter;

    private boolean mIsDarkSearchTheme = false;

    private String mLastQuery = "";

    public SlidingSearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  wordService= APIUtils.getWordService();
      //  getWords();
//        if(Utils.loadInternalFile(getActivity())==null){
//            Utils.writeToInternalFile(getActivity(),Utils.list);
//            Log.i("file",Utils.loadInternalFile(getActivity()).size()+"");
//        }
        fillSuggest(Utils.list);
        //fillSuggest(Utils.loadInternalFile(getActivity()));
        //Log.i("nk",Utils.loadInternalFile(getActivity()).size()+"");
        return inflater.inflate(R.layout.fragment_sliding_search_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mSearchResultsList = (RecyclerView) view.findViewById(R.id.search_results_list);
        setupFloatingSearch();
        setupResultsList();
        if(Utils.getHistory.size()!=0) {
            mSearchResultsAdapter.swapData(Utils.getHistory);
        }
        setupDrawer();
    }

    private void setupFloatingSearch() {
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    WordDataHelper.findSuggestions(getActivity(), newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY,list, new WordDataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<WordSuggestion> results) {
                                    Log.i("word1","ahdsa");
                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mSearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mSearchView.hideProgress();
                                }
                            });
                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {
                WordSuggestion colorSuggestion = (WordSuggestion) searchSuggestion;
                if(checkTrung(((WordSuggestion) searchSuggestion).getMatu())) {
                    Utils.getHistory.add(new Word(((WordSuggestion) searchSuggestion).getMatu(), searchSuggestion.getBody(), ((WordSuggestion) searchSuggestion).getDetail()));
                }
                WordDataHelper.findWords(getActivity(), colorSuggestion.getBody(),Utils.list,
                        new WordDataHelper.OnFindWordsListener() {
                            @Override
                            public void onResults(List<Word> results) {
                                Log.i("size",Utils.list.size()+"");
                                mSearchResultsAdapter.swapData(results);
                            }

                        });
//                Intent i = new Intent(getActivity(),DetailActivity.class);
//                i.putExtra("word",colorSuggestion.getBody());
//                startActivity(i);
                Log.d(TAG, "onSuggestionClicked()");

                mLastQuery = searchSuggestion.getBody();
            }

            @Override
            public void onSearchAction(String query) {
                mLastQuery = query;
                WordDataHelper.findWords(getActivity(), query,Utils.list,
                        new WordDataHelper.OnFindWordsListener() {

                            @Override
                            public void onResults(List<Word> results) {

                                mSearchResultsAdapter.swapData(results);
                            }

                        });
                Log.d(TAG, "onSearchAction()");
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                //show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(WordDataHelper.getHistory(getActivity(), 3,list));

                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                //set the title of the bar so that when focus is returned a new query begins
                mSearchView.setSearchBarTitle(mLastQuery);

                //you can also set setSearchText(...) to make keep the query there when not focused and when focus returns
                //mSearchView.setSearchText(searchSuggestion.getBody());

                Log.d(TAG, "onFocusCleared()");
            }
        });


        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_change_colors) {
                    if(!mIsDarkSearchTheme) {
                        mIsDarkSearchTheme = true;
                        //demonstrate setting colors for items
                        mSearchView.setBackgroundColor(Color.parseColor("#787878"));
                        mSearchView.setViewTextColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setHintTextColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setActionMenuOverflowColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setMenuItemIconColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setClearBtnColor(Color.parseColor("#e9e9e9"));
                        mSearchView.setDividerColor(Color.parseColor("#BEBEBE"));
                        mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
                    }
                    else {
                        mIsDarkSearchTheme = false;
                        //demonstrate setting colors for items
                        mSearchView.setBackgroundColor(Color.parseColor("#fafafa"));
                        mSearchView.setViewTextColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setHintTextColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setActionMenuOverflowColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setMenuItemIconColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setLeftActionIconColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setClearBtnColor(Color.parseColor("#2e2c2c"));
                        mSearchView.setDividerColor(Color.parseColor("#BEBEBE"));
                        mSearchView.setLeftActionIconColor(Color.parseColor("#2e2c2c"));
                    }

                } else {
                    //just print action
                    mSearchView.setSearchText("");
                    Toast.makeText(getActivity().getApplicationContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

                Log.d(TAG, "onHomeClicked()");
            }
        });

        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                WordSuggestion colorSuggestion = (WordSuggestion) item;

                String textColor = mIsDarkSearchTheme ? "#ffffff" : "#000000";
                String textLight = mIsDarkSearchTheme ? "#bfbfbf" : "#787878";

                if (colorSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = colorSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font color=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(Html.fromHtml(text));
            }

        });

        //listen for when suggestion list expands/shrinks in order to move down/up the
        //search results list
        mSearchView.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {
                mSearchResultsList.setTranslationY(newHeight);
            }
        });

        /*
         * When the user types some text into the search field, a clear button (and 'x' to the
         * right) of the search text is shown.
         *
         * This listener provides a callback for when this button is clicked.
         */
        mSearchView.setOnClearSearchActionListener(new FloatingSearchView.OnClearSearchActionListener() {
            @Override
            public void onClearSearchClicked() {

                Log.d(TAG, "onClearSearchClicked()");
            }
        });
    }

    private void setupResultsList() {
        mSearchResultsAdapter = new WordResultList();
        mSearchResultsList.setAdapter(mSearchResultsAdapter);
        mSearchResultsList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public boolean onActivityBackPress() {
        //if mSearchView.setSearchFocused(false) causes the focused search
        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
        //returns false, we know that the search was already closed so the call didn't change the focus
        //state and it makes sense to call supper onBackPressed() and close the activity
        if (!mSearchView.setSearchFocused(false)) {
            return false;
        }
        return true;
    }

    private void setupDrawer() {
        attachSearchViewActivityDrawer(mSearchView);
    }

    public void fillSuggest(List<Word> ls){
        for (Word w:ls
        ) {
            list.add(new WordSuggestion(w.getMatu(),w.getWord(),w.getDetail()));
//            Utils.list.add(w);
        }
        Log.i("list",Utils.list.size()+"");
    }

    public boolean checkTrung(int ma){
        for (Word w:Utils.getHistory
             ) {
            if(w.getMatu()==ma)
                return  false;
        }
        return  true;
    }

}