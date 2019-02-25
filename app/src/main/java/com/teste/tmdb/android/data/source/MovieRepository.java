package com.teste.tmdb.android.data.source;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;
import com.teste.tmdb.android.data.viewmodel.VideoViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;


@Singleton
public class MovieRepository implements IMovieDataSource {

    @NonNull
    private final IMovieDataSource mMovieRemoteDataSource;

    @NonNull
    private final ContentResolver mContentResolver;

    @Inject
    public MovieRepository(@NonNull IMovieDataSource movieRemoteDataSource,
                           @NonNull ContentResolver contentResolver) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
        this.mContentResolver = contentResolver;
    }

    @Override
    public Call<GenreList> getGenres() {
        return mMovieRemoteDataSource.getGenres();
    }

    @Override
    public Observable<List<MovieViewModel>> getDiscoveryMovies(long genreId) {
        return mMovieRemoteDataSource.getDiscoveryMovies(genreId);
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }


    @Override
    public Observable<List<VideoViewModel>> getTrailers(long movieId) {
        return mMovieRemoteDataSource.getTrailers(movieId);
    }

    @Override
    public Observable<List<ReviewViewModel>> getReviews(long movieId) {
        return mMovieRemoteDataSource.getReviews(movieId);
    }

}
