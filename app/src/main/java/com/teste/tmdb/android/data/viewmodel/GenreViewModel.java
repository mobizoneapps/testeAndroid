package com.teste.tmdb.android.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;



public class GenreViewModel implements Parcelable {

    private long mId;
    private String mName;

    public GenreViewModel(long id, String name) {
        this.mId = id;
        this.mName = name;
    }

    protected GenreViewModel(Parcel in) {
        this.mId = in.readLong();
        this.mName = in.readString();
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeString(this.mName);
    }

    public static final Creator<GenreViewModel> CREATOR = new Creator<GenreViewModel>() {
        @Override
        public GenreViewModel createFromParcel(Parcel source) {

            return new GenreViewModel(source);
        }

        @Override
        public GenreViewModel[] newArray(int size) {

            return new GenreViewModel[size];
        }
    };
}
