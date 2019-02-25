package com.teste.tmdb.android.data.model;

import android.support.annotation.NonNull;

import com.teste.tmdb.android.BuildConfig;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;


public class Movie implements IViewModel<MovieViewModel> {

    private static final String PHOTO_SMALL_SIZE = "w185";
    private static final String PHOTO_LARGE_SIZE = "w300";

    private long id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;
    private String release_date;
    private String backdrop_path;

    @NonNull
    @Override
    public MovieViewModel toViewModel() {
        return new MovieViewModel(id, title,
                BuildConfig.BASE_URL_TMDB_IMAGE + PHOTO_SMALL_SIZE + poster_path,
                overview, vote_average, release_date, BuildConfig.BASE_URL_TMDB_IMAGE + PHOTO_LARGE_SIZE + backdrop_path);
    }
}
