package com.teste.tmdb.android.ui.moviedetail;


import com.teste.tmdb.android.ApplicationComponent;
import com.teste.tmdb.android.util.ActivityScoped;

import dagger.Component;


@ActivityScoped
@Component(modules = MovieDetailModule.class, dependencies = ApplicationComponent.class)
public interface IMovieDetailComponent {

    void inject(MovieDetailActivity activity);
}
