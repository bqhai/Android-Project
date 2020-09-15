package com.example.tudienanhviet.SearchView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import  com.example.tudienanhviet.R;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.util.Util;
import com.example.tudienanhviet.Word;

import java.util.ArrayList;
import java.util.List;

public class WordResultList extends RecyclerView.Adapter<WordResultList.ViewHolder> {
    private List<Word> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;

    public interface OnItemClickListener{
        void onClick(Word wordWrapper);
    }

    private WordResultList.OnItemClickListener mItemsOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mColorName;
        public final TextView mColorValue;
        public final View mTextContainer;
        public  final ImageButton btnSave;

        public ViewHolder(View view) {
            super(view);
            mColorName = (TextView) view.findViewById(R.id.color_name);
            mColorValue = (TextView) view.findViewById(R.id.color_value);
            mTextContainer = view.findViewById(R.id.text_container);
            btnSave=view.findViewById(R.id.btnSaveToYours);
            btnSave.setImageResource(R.drawable.ic_star);
        }
    }

    public void swapData(List<Word> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void setItemsOnClickListener(WordResultList.OnItemClickListener onClickListener){
        this.mItemsOnClickListener = onClickListener;
    }

    @Override
    public WordResultList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_listitem, parent, false);

        return new WordResultList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WordResultList.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final Word wordSuggestion = mDataSet.get(position);
        holder.mColorName.setText(wordSuggestion.getWord());
        holder.mColorValue.setText(wordSuggestion.getDetail());
        if(!checkTrung(wordSuggestion.getMatu())) {
            holder.btnSave.setImageResource(R.drawable.ic_history_black_24dp);
        }
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(checkTrung(wordSuggestion.getMatu())) {
                        Utils.getYourWords.add(wordSuggestion);
                        Log.i("your", Utils.getYourWords.size() + "");
                        holder.btnSave.setImageResource(R.drawable.ic_history_black_24dp);

                    }else {

                        holder.btnSave.setImageResource(R.drawable.ic_star);
//                        Utils.getYourWords.remove(wordSuggestion);
                       Utils.getYourWords.remove(delete(wordSuggestion));
                        Log.i("your", Utils.getYourWords.size() + "");
                        Log.i("delete", wordSuggestion.getMatu() + "");
                    }
                }

        });


//        int color = Color.parseColor(colorSuggestion.getHex());
//        holder.mColorName.setTextColor(color);
//        holder.mColorValue.setTextColor(color);

        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

        if(mItemsOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemsOnClickListener.onClick(mDataSet.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }

    public boolean checkTrung(int ma){
        for (Word w:Utils.getYourWords
        ) {

            if(w.getMatu()==ma){
                return  false;
            }
        }
        return  true;
    }
    public Word delete(Word w){
        for (Word w1:Utils.getYourWords
        ) {
            Log.i("yo1", w.getMatu() + "");
            if(w1.getMatu()==w.getMatu()){
                return w1;
            }
        }
        return w;
    }
}
