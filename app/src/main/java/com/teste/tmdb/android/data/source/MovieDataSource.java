package com.teste.tmdb.android.data.source;

import android.support.annotation.NonNull;

import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.model.PaginatedList;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;
import com.teste.tmdb.android.data.viewmodel.VideoViewModel;
import com.teste.tmdb.android.network.IMovieApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

public class MovieDataSource implements IMovieDataSource {

    @NonNull
    private final IMovieApi mIMovieApi;

    @Inject
    public MovieDataSource(@NonNull IMovieApi mIMovieApi) {
        this.mIMovieApi = mIMovieApi;
    }

    @Override
    public Call<GenreList> getGenres() {
        return mIMovieApi.getGenres();
    }


    @Override
    public Observable<List<MovieViewModel>> getDiscoveryMovies(long genreId) {
        return mIMovieApi.getDiscoveryMovies(genreId)
                .map(PaginatedList::toListViewModel);
    }


    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mIMovieApi.getPopularMovies()
                .map(PaginatedList::toListViewModel);
    }

    @Override
    public Observable<List<VideoViewModel>> getTrailers(long movieId) {
        return mIMovieApi.getTrailers(movieId)
                .map(PaginatedList::toListViewModel);
    }

    @Override
    public Observable<List<ReviewViewModel>> getReviews(long movieId) {
        return mIMovieApi.getReviews(movieId)
                .map(PaginatedList::toListViewModel);
    }


}
