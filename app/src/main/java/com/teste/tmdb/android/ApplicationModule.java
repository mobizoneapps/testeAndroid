package com.teste.tmdb.android;

import android.content.ContentResolver;
import android.content.Context;

import com.teste.tmdb.android.network.ApiFactory;
import com.teste.tmdb.android.network.IMovieApi;
import com.teste.tmdb.android.util.scheduler.BaseSchedulerProvider;
import com.teste.tmdb.android.util.scheduler.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    ApiFactory providesApiFactory() {
        return new ApiFactory(BuildConfig.BASE_URL_TMDB_API);
    }

    @Provides
    @Singleton
    IMovieApi providesMovieApi(final ApiFactory apiFactory) {
        return apiFactory.create(IMovieApi.class);
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providesSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    ContentResolver providesContentResolver() {
        return mContext.getContentResolver();
    }
}
