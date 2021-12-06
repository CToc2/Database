package com.example.moviedatabase;

public class Movie {
    private int movieID;
    private String Title;
    private String Director;
    private String Year;

    public Movie() {
        movieID = -1;
    }
    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle(){ return Title;}

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDirector(){ return Director;}

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getYear(){ return Year;}

    public void setYear(String Year) { this.Year = Year;}


}
