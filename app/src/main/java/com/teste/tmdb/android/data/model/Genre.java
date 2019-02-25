package com.teste.tmdb.android.data.model;

import android.support.annotation.NonNull;

import com.teste.tmdb.android.data.viewmodel.GenreViewModel;

public class Genre implements IViewModel<GenreViewModel> {

    private long id;
    private String name;

    @NonNull
    @Override
    public GenreViewModel toViewModel() {
        return new GenreViewModel(id, name);
    }
}
