package com.example.tudienanhviet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

class CoursesAdapter extends ArrayAdapter<Course> {
    int layout;
    Context con;
    ArrayList<Course> arr= new ArrayList<>();

    public CoursesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> objects) {
        super(context, resource, objects);
        this.arr=objects;
        this.layout=resource;
        this.con=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course c=arr.get(position);
        convertView = LayoutInflater.from(con).inflate(layout,parent,false);
        TextView textView_Name=convertView.findViewById(R.id.textViewName);
        TextView textView_Detail=convertView.findViewById(R.id.textViewDescription);
        ImageView img= convertView.findViewById(R.id.imageViewCourses);
        img.setImageResource(R.drawable.ic_book);
        textView_Name.setText(c.getCourseName());
        textView_Detail.setText(c.getCourseDetail());
        return convertView;

    }

}
