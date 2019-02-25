package com.teste.tmdb.android.ui.movielist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.teste.tmdb.android.MovieApplication;
import com.teste.tmdb.android.R;
import com.teste.tmdb.android.data.model.Genre;
import com.teste.tmdb.android.data.model.GenreList;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.ui.moviedetail.MovieDetailActivity;
import com.teste.tmdb.android.util.ViewUtils;
import com.teste.tmdb.android.widget.OnItemClickListener;
import com.teste.tmdb.android.widget.RecycleBorderItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity
        implements IMovieList.View,
                    SwipeRefreshLayout.OnRefreshListener,
        OnItemClickListener {

    static final String STATE_DATA = MovieListActivity.class.getName() + "STATE_DATA";

    @Inject
    MovieListPresenter mPresenter;

    private SwipeRefreshLayout mSwipeRefresh;
    private MovieListAdapter mAdapter;
    private GenreListAdapter mGenreAdapter;
    private RecyclerView recyclerViewGenres;
    private RecyclerView recyclerView;
    private Genre selectedGenre;
    private TextView labelMostPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        labelMostPopular = (TextView) findViewById(R.id.labelMostPopular);


        recyclerViewGenres = (RecyclerView) findViewById(R.id.recycler_view_genres);  mGenreAdapter = new GenreListAdapter(this);


        mGenreAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {

                selectedGenre = mGenreAdapter.getData().get(position);

                mPresenter.loadMovies(true, selectedGenre.toViewModel().getId());

                labelMostPopular.setText(selectedGenre.toViewModel().getName());

            }
        });


        recyclerViewGenres.setAdapter(mGenreAdapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenres.setLayoutManager(linearLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MovieListAdapter(this);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.addItemDecoration(new RecycleBorderItemDecoration(10, 10));
        recyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(this);

        DaggerIMovieListComponent.builder()
                .applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent())
                .movieListModule(new MovieListModule(this))
                .build()
                .inject(this);



        mPresenter.subscribe();
        if (savedInstanceState == null) {
            mPresenter.loadMovies(true,0);
            ViewUtils.setSwipeRefreshing(mSwipeRefresh, true);
        } else {

            mAdapter.addData(savedInstanceState.getParcelableArrayList(STATE_DATA));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(STATE_DATA, new ArrayList<>(mAdapter.getData()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void clearMovieList() {
        mAdapter.clearData();
    }

    @Override
    public void showMovieList(List<MovieViewModel> movieViewModels) {
        mAdapter.addData(movieViewModels);
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }

    @Override
    public void showError() {
        Snackbar.make(mSwipeRefresh, R.string.error_load_movies, Snackbar.LENGTH_LONG).show();
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }

    @Override
    public void showGenres(GenreList genreViewModels) {

        mGenreAdapter.addData(genreViewModels.getGenres());


    }

    @Override
    public void onRefresh() {
        mPresenter.loadMovies(true, 0);
        labelMostPopular.setText(getText(R.string.most_popular));
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, true);
    }

    @Override
    public void onItemClicked(int position) {
        startActivity(MovieDetailActivity.newIntent(this, mAdapter.getData().get(position)));
    }

}
