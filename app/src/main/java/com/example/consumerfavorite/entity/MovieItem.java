package com.example.consumerfavorite.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.consumerfavorite.database.DatabaseContract;
import com.google.gson.annotations.SerializedName;

import static com.example.consumerfavorite.database.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.consumerfavorite.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.consumerfavorite.database.DatabaseContract.MovieColumns.POSTER;
import static com.example.consumerfavorite.database.DatabaseContract.MovieColumns.TITLE;

public class MovieItem implements Parcelable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public MovieItem(Cursor cursor) {
        this.id = DatabaseContract.getColumnInt(cursor, MOVIE_ID);
        this.title = DatabaseContract.getColumnString(cursor, TITLE);
        this.overview = DatabaseContract.getColumnString(cursor, OVERVIEW);
        this.posterPath = DatabaseContract.getColumnString(cursor, POSTER);
    }

    private int id;

    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    protected MovieItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        overview = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
    }
}
