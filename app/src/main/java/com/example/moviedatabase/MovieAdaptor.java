package com.example.moviedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdaptor extends RecyclerView.Adapter{
    private ArrayList<Movie> movieData;
    private View.OnClickListener onClickListener;


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewDirector;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDirector = itemView.findViewById(R.id.textViewDirector);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getTitleTextView() {
            return textViewTitle;
        }

        public TextView getDirectorTextView() {
            return textViewDirector;
        }

    }
    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }
    public MovieAdaptor(ArrayList<Movie> arrayList){
        movieData = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder cvh = (MovieViewHolder) holder;
        cvh.getTitleTextView().setText(movieData.get(position).getTitle());
        cvh.getDirectorTextView().setText(movieData.get(position).getDirector());
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }
}
