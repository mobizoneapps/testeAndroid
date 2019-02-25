package com.teste.tmdb.android.ui.moviedetail;

import com.teste.tmdb.android.data.viewmodel.MovieViewModel;

import dagger.Module;
import dagger.Provides;


@Module
public class MovieDetailModule {

    private final long mMovieId;
    private final IMovieDetail.View mView;

    public MovieDetailModule(MovieViewModel movieViewModel, IMovieDetail.View view) {
        this.mMovieId = movieViewModel.getId();
        this.mView = view;
    }

    @Provides
    IMovieDetail.View providesView() {
        return mView;
    }

    @Provides
    long providesMovieId() {
        return mMovieId;
    }
}
