package com.teste.tmdb.android.ui.movielist;

import dagger.Module;
import dagger.Provides;


@Module
public class MovieListModule {

    private final IMovieList.View mView;

    public MovieListModule(IMovieList.View mView) {
        this.mView = mView;
    }

    @Provides
    IMovieList.View providesView() {
        return mView;
    }
}
