package com.teste.tmdb.android.network;

import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.model.Movie;
import com.teste.tmdb.android.data.model.PaginatedList;
import com.teste.tmdb.android.data.model.Review;
import com.teste.tmdb.android.data.model.Video;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;
import com.teste.tmdb.android.data.viewmodel.VideoViewModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IMovieApi {

    @GET("/3/genre/movie/list")
    Call<GenreList> getGenres();

    @GET("/3/discover/movie")
    Observable<PaginatedList<Movie, MovieViewModel>> getDiscoveryMovies(@Query("with_genres") long genreId);

    @GET("/3/movie/popular")
    Observable<PaginatedList<Movie, MovieViewModel>> getPopularMovies();

    @GET("/3/movie/{movieId}/videos")
    Observable<PaginatedList<Video, VideoViewModel>> getTrailers(@Path("movieId") long movieId);

    @GET("/3/movie/{movieId}/reviews")
    Observable<PaginatedList<Review, ReviewViewModel>> getReviews(@Path("movieId") long movieId);
}
