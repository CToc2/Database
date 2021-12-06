package com.example.moviedatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    private Movie currentMovie;

    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(UserInfo.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);

        currentMovie = new Movie();
//*****

        initSaveButton();
        initTextChangedEvents();

    }

    private void initTextChangedEvents(){
        final EditText etMovieTitle = findViewById(R.id.editTitle);
        etMovieTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMovie.setTitle(etMovieTitle.getText().toString());
                currentMovie.setMovieID(-1);
            }
        });

        final EditText etDirector = findViewById(R.id.editDirector);
        etDirector.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMovie.setDirector(etDirector.getText().toString());
            }
        });

        final EditText etYear = findViewById(R.id.editYear);
        etYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMovie.setYear(etYear.getText().toString());
            }
        });
    }

    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position != -1){
            MovieDataSource ds = new MovieDataSource(this);
            try {
                ds.open();
                currentMovie = ds.getMovie(position+1);
                ds.close();
                EditText nameEdit = findViewById(R.id.editTitle);
                nameEdit.setText(currentMovie.getTitle());
                EditText addressEdit = findViewById(R.id.editDirector);
                addressEdit.setText(currentMovie.getDirector());
                EditText cityEdit = findViewById(R.id.editYear);
                cityEdit.setText(currentMovie.getYear());
            } catch (Exception e){
                Toast.makeText(this, "Error accessing contact", Toast.LENGTH_LONG).show();
            }

        }
    }

//    private void initSaveButton(){
//        Button listButton = findViewById(R.id.save);
//        listButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(UserInfo.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });
//    }
private void initSaveButton(){
    Button saveButton = findViewById(R.id.save);
    saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean wasSuccessful;
            MovieDataSource ds = new MovieDataSource(UserInfo.this);
            ArrayList<Movie> movies;
            try {
                ds.open();
                if (currentMovie.getMovieID() == -1){
                    wasSuccessful = ds.insertMovie(currentMovie);
                    if (wasSuccessful){
                        int newId = ds.getLastMovieID();
                        currentMovie.setMovieID(newId);
                    }
                }else{
                    wasSuccessful = ds.updateContact(currentMovie);
                }
                ds.close();
            }catch (Exception e){
                wasSuccessful = false;
            }
            Intent intent = new Intent(UserInfo.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

//            if (wasSuccessful){
//                ToggleButton editToggle = findViewById(R.id.toggleButton);
//                editToggle.toggle();
//                setForEditing(false);
//            }
        }
    });
}
}
