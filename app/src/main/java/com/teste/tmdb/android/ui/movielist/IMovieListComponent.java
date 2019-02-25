package com.teste.tmdb.android.ui.movielist;

import com.teste.tmdb.android.ApplicationComponent;
import com.teste.tmdb.android.util.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(modules = MovieListModule.class, dependencies = ApplicationComponent.class)
public interface IMovieListComponent {

    void inject(MovieListActivity activity);
}
