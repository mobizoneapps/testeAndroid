package com.teste.tmdb.android.ui.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teste.tmdb.android.R;
import com.teste.tmdb.android.data.model.Genre;
import com.teste.tmdb.android.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Genre> mData;
    private OnItemClickListener mOnItemClickListener;

    GenreListAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_genre, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTextView.setOnClickListener((v) -> {
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

        Genre genre = mData.get(position);

        holder.mTextView.setText(genre.toViewModel().getName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Genre> getData() {
        // defensive copy
        return new ArrayList<>(mData);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    void addData(final List<Genre> data) {
        final int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
