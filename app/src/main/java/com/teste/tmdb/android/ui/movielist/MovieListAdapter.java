package com.teste.tmdb.android.ui.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.teste.tmdb.android.R;
import com.teste.tmdb.android.data.viewmodel.MovieViewModel;
import com.teste.tmdb.android.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<MovieViewModel> mData;
    private OnItemClickListener mOnItemClickListener;

    MovieListAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImageView.setOnClickListener((v) -> {
            if (viewHolder.getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClicked(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieViewModel movieViewModel = mData.get(position);
        Picasso.with(mContext)
                .load(movieViewModel.getPosterUrl())
                .fit()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<MovieViewModel> getData() {
        // defensive copy
        return new ArrayList<>(mData);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    void addData(final List<MovieViewModel> data) {
        final int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }
}
