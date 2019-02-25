package com.teste.tmdb.android.data.model;

import com.teste.tmdb.android.data.viewmodel.ReviewViewModel;

public class Review implements IViewModel<ReviewViewModel> {

    private String id;
    private String author;
    private String content;

    @Override
    public ReviewViewModel toViewModel() {
        return new ReviewViewModel(id, author, content);
    }
}
