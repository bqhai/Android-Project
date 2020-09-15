package com.example.tudienanhviet.SearchView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.util.Util;
import com.example.tudienanhviet.IrregularVerbs;
import com.example.tudienanhviet.R;
import com.example.tudienanhviet.Word;

import java.util.ArrayList;
import java.util.List;

public class IrregularVerbAdapter extends RecyclerView.Adapter<IrregularVerbAdapter.ViewHolder> {
    private List<IrregularVerbs> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;

    public interface OnItemClickListener{
        void onClick(Word wordWrapper);
    }

    private WordResultList.OnItemClickListener mItemsOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mv1;
        public final TextView mv2;
        public final TextView mv3;
        public final TextView detail;


        public ViewHolder(View view) {
            super(view);
            mv1 = (TextView) view.findViewById(R.id.txt_v1);
            mv2 = (TextView) view.findViewById(R.id.txt_v2);
            mv3 = view.findViewById(R.id.txtv3);
            detail=view.findViewById(R.id.txt_irre_detail);


        }
    }

    public void swapData(List<IrregularVerbs> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void setItemsOnClickListener(WordResultList.OnItemClickListener onClickListener){
        this.mItemsOnClickListener = onClickListener;
    }

    @Override
    public IrregularVerbAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ireegularverbs_listitem, parent, false);

        return new IrregularVerbAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IrregularVerbAdapter.ViewHolder holder,@SuppressLint("RecyclerView") final int position) {
        final IrregularVerbs irre = mDataSet.get(position);
        holder.mv1.setText(irre.getV1());
        holder.mv2.setText(irre.getV2());
        holder.mv3.setText(irre.getV3());
        holder.detail.setText(irre.getDetail());
//        int color = Color.parseColor(colorSuggestion.getHex());
//        holder.mColorName.setTextColor(color);
//        holder.mColorValue.setTextColor(color);

        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

//        if(mItemsOnClickListener != null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mItemsOnClickListener.onClick(mDataSet.get(position));
//                }
//            });
//        }
    }

//    @Override
//    public void onBindViewHolder(final WordResultList.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//
//
//    }

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
//
//    public boolean checkTrung(int ma){
//        for (Word w:Utils.getYourWords
//        ) {
//
//            if(w.getMatu()==ma){
//                return  false;
//            }
//        }
//        return  true;
//    }
//    public Word delete(Word w){
//        for (Word w1:Utils.getYourWords
//        ) {
//            Log.i("yo1", w.getMatu() + "");
//            if(w1.getMatu()==w.getMatu()){
//                return w1;
//            }
//        }
//        return w;
//    }
}