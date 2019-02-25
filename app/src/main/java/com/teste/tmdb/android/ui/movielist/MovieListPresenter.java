package com.teste.tmdb.android.ui.movielist;

import android.util.Log;

import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.source.MovieRepository;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.util.scheduler.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MovieListPresenter implements IMovieList.Presenter {

    private final IMovieList.View mMovieListView;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    MovieListPresenter(IMovieList.View movieListView,
                       MovieRepository movieRepository,
                       BaseSchedulerProvider schedulerProvider) {
        this.mMovieListView = movieListView;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;

        this.mComposite = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        // no-op
    }

    @Override
    public void unsubscribe() {
        this.mComposite.clear();
    }

    @Override
    public void loadMovies(final boolean refresh,  final long genreId) {


        mComposite.add(getObservable(genreId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(movieViewModels -> {
                    processMovieList(refresh, movieViewModels);
                }, throwable -> mMovieListView.showError(), () -> {}));


        mMovieRepository.getGenres().enqueue(new Callback<GenreList>() {
            @Override
            public void onResponse(Call<GenreList> call, Response<GenreList> response) {
                processGenreList(response.body());
            }

            @Override
            public void onFailure(Call<GenreList> call, Throwable t) {
                Log.v("Error", t.getMessage());
            }
        });

    }

    private Observable<List<MovieViewModel>> getObservable(long genreId) {

        if (genreId > 0) {

            return mMovieRepository.getDiscoveryMovies(genreId);

        } else {

            return mMovieRepository.getPopularMovies();
        }

    }

    private void processMovieList(final boolean refresh,
                                  final List<MovieViewModel> movieViewModels) {
        if (refresh) {
            mMovieListView.clearMovieList();
        }
        mMovieListView.showMovieList(movieViewModels);
    }


    private void processGenreList(final GenreList genreViewModels) {

        mMovieListView.showGenres(genreViewModels);

    }
}
