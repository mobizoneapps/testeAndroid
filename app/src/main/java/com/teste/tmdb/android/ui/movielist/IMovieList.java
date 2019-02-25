package com.teste.tmdb.android.ui.movielist;

import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.ui.IPresenter;

import java.util.List;

public interface IMovieList {

    interface View {
        void clearMovieList();

        void showMovieList(final List<MovieViewModel> movieViewModels);

        void showError();

        void showGenres(final GenreList genreViewModels);
    }

    interface Presenter extends IPresenter {

        void loadMovies(boolean refresh, long genreId);

    }
}
