package com.teste.tmdb.android.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;


public class MovieViewModel implements Parcelable {
    private long mId;
    private String mTitle;
    private String mPosterUrl;
    private String mPlotSynopsis;
    private double mUserRating;
    private String mReleaseDate;
    private String mBackgroundUrl;

    public MovieViewModel(long id, String title, String posterUrl, String plotSynopsis,
                          double userRating, String releaseDate, String backgroundUrl) {
        this.mId = id;
        this.mTitle = title;
        this.mPosterUrl = posterUrl;
        this.mPlotSynopsis = plotSynopsis;
        this.mUserRating = userRating;
        this.mReleaseDate = releaseDate;
        this.mBackgroundUrl = backgroundUrl;
    }

    protected MovieViewModel(Parcel in) {
        this.mId = in.readLong();
        this.mTitle = in.readString();
        this.mPosterUrl = in.readString();
        this.mPlotSynopsis = in.readString();
        this.mUserRating = in.readDouble();
        this.mReleaseDate = in.readString();
        this.mBackgroundUrl = in.readString();
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getmBackgroundUrl() {
        return mBackgroundUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mPosterUrl);
        dest.writeString(this.mPlotSynopsis);
        dest.writeDouble(this.mUserRating);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mBackgroundUrl);
    }

    public static final Creator<MovieViewModel> CREATOR = new Creator<MovieViewModel>() {
        @Override
        public MovieViewModel createFromParcel(Parcel source) {
            return new MovieViewModel(source);
        }

        @Override
        public MovieViewModel[] newArray(int size) {
            return new MovieViewModel[size];
        }
    };
}
