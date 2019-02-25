package com.teste.tmdb.android.ui.moviedetail;

import com.teste.tmdb.android.data.source.MovieRepository;
import com.teste.tmdb.android.util.scheduler.BaseSchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;


class MovieDetailPresenter implements IMovieDetail.Presenter {

    private final long mMovieId;
    private final IMovieDetail.View mMovieDetailView;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    MovieDetailPresenter(@NonNull long movieId,
                         IMovieDetail.View movieDetailView,
                         MovieRepository movieRepository,
                         BaseSchedulerProvider schedulerProvider) {
        this.mMovieId = movieId;
        this.mMovieDetailView = movieDetailView;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;

        this.mComposite = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        //no-op
    }

    @Override
    public void unsubscribe() {
        this.mComposite.clear();
    }

    @Override
    public void loadTrailers() {
        mComposite.add(mMovieRepository.getTrailers(mMovieId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(mMovieDetailView::showTrailers,
                        (t) -> mMovieDetailView.showTrailers(new ArrayList<>()), () -> {}));
    }

    @Override
    public void loadReviews() {
        mComposite.add(mMovieRepository.getReviews(mMovieId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(mMovieDetailView::showReviews,
                        (t) -> mMovieDetailView.showReviews(new ArrayList<>()), () -> {}));
    }

}
