package com.teste.tmdb.android;


import com.teste.tmdb.android.data.source.IMovieDataSource;
import com.teste.tmdb.android.data.source.MovieDataSource;
import com.teste.tmdb.android.network.IMovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = ApplicationModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    IMovieDataSource providesMovieRemoteDataSource(IMovieApi IMovieApi) {
        return new MovieDataSource(IMovieApi);
    }
}
