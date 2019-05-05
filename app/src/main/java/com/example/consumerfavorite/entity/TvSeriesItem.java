package com.example.consumerfavorite.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.consumerfavorite.database.DatabaseContract;
import com.google.gson.annotations.SerializedName;

import static com.example.consumerfavorite.database.DatabaseContract.TvColumns.OVERVIEW_TV;
import static com.example.consumerfavorite.database.DatabaseContract.TvColumns.POSTER_TV;
import static com.example.consumerfavorite.database.DatabaseContract.TvColumns.TITLE_TV;
import static com.example.consumerfavorite.database.DatabaseContract.TvColumns.TV_ID;

public class TvSeriesItem implements Parcelable {

    private  int id;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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



    public TvSeriesItem(Cursor cursor) {
        this.id = DatabaseContract.getColumnInt(cursor, TV_ID);
        this.originalName = DatabaseContract.getColumnString(cursor, TITLE_TV);
        this.overview = DatabaseContract.getColumnString(cursor, OVERVIEW_TV);
        this.posterPath = DatabaseContract.getColumnString(cursor, POSTER_TV);
    }

    protected TvSeriesItem(Parcel in) {
        id = in.readInt();
        originalName = in.readString();
        posterPath = in.readString();
        overview = in.readString();
    }

    public static final Creator<TvSeriesItem> CREATOR = new Creator<TvSeriesItem>() {
        @Override
        public TvSeriesItem createFromParcel(Parcel in) {
            return new TvSeriesItem(in);
        }

        @Override
        public TvSeriesItem[] newArray(int size) {
            return new TvSeriesItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(originalName);
        dest.writeString(posterPath);
        dest.writeString(overview);
    }
}
