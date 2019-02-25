package com.teste.tmdb.android.ui.moviedetail;

import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;
import com.teste.tmdb.android.data.viewmodel.VideoViewModel;
import com.teste.tmdb.android.ui.IPresenter;

import java.util.List;

public interface IMovieDetail {

    interface View  {
        void showTrailers(List<VideoViewModel> videoViewModels);

        void showReviews(List<ReviewViewModel> reviewViewModels);
    }

    interface Presenter extends IPresenter {

        void loadTrailers();
        void loadReviews();

    }
}
