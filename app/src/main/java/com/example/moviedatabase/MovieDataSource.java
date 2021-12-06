package com.example.moviedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDataSource {

    private SQLiteDatabase database;
    private MovieDBHelper dbHelper;

    public MovieDataSource(Context context){
        dbHelper = new MovieDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertMovie(Movie c) {
        boolean didSucceed = false;
        try{
            ContentValues values = new ContentValues();
            values.put("title", c.getTitle());
            values.put("director", c.getDirector());
            values.put("year", c.getYear());
            didSucceed = database.insert("contact", null, values) > 0;
        }catch (Exception e){

        }
        return didSucceed;
    }

    public boolean updateContact(Movie c) {
        boolean didSucceed = false;
        try{
            ContentValues values = new ContentValues();
            values.put("title", c.getTitle());
            values.put("director", c.getDirector());
            values.put("year", c.getYear());
            Long id = (long) c.getMovieID();
            didSucceed = database.update("contact", values, "_id="+id, null) > 0;
        }catch (Exception e){

        }
        return didSucceed;
    }

    public int getLastMovieID(){
        int lastId = -1;
        try{
            String query = "Select Max(_id) from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }catch (Exception e){

        }
        return lastId;
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try{
            String query = "Select * from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Movie c = new Movie();
                c.setTitle(cursor.getString(1));
                c.setDirector(cursor.getString(2));
                c.setYear(cursor.getString(3));
                movies.add(c);
                cursor.moveToNext();
            }
            cursor.close();
        }catch(Exception e){

        }
        return movies;
    }

    public Movie getMovie(int id) {
        Movie c = new Movie();
        try{
            String query = "Select * from contact where _id="+id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            c.setMovieID(id);
            c.setTitle(cursor.getString(1));
            c.setDirector(cursor.getString(2));
            c.setYear(cursor.getString(3));
            cursor.close();
        }catch(Exception e){

        }
        return c;
    }
}
