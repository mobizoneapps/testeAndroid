package com.teste.tmdb.android.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public class RecycleBorderItemDecoration extends RecyclerView.ItemDecoration {

    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public RecycleBorderItemDecoration(int horizontalSpacing, int verticalSpacing) {
        this.mHorizontalSpacing = horizontalSpacing;
        this.mVerticalSpacing = verticalSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = 1;

        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }

        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        outRect.left = column == 0 ? 0 : mHorizontalSpacing / 2;
        outRect.right = column == spanCount - 1 ? 0 : mHorizontalSpacing / 2;
        if (position >= spanCount) {
            outRect.top = mVerticalSpacing;
        }
    }
}

