package com.teste.tmdb.android.data.source;


import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;
import com.teste.tmdb.android.data.viewmodel.VideoViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;


public interface IMovieDataSource {

    Call<GenreList> getGenres();

    Observable<List<MovieViewModel>> getDiscoveryMovies(final long genreId);

    Observable<List<MovieViewModel>> getPopularMovies();

    Observable<List<VideoViewModel>> getTrailers(final long movieId);

    Observable<List<ReviewViewModel>> getReviews(final long movieId);
}
