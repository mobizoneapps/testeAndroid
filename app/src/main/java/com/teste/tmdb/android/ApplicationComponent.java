package com.teste.tmdb.android;



import com.teste.tmdb.android.data.source.MovieRepository;
import com.teste.tmdb.android.util.scheduler.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = { ApplicationModule.class, RepositoryModule.class })
public interface ApplicationComponent {

    MovieRepository getMovieRepository();
    BaseSchedulerProvider getSchedulerProvider();
}
