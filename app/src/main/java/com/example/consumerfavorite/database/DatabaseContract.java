package com.example.consumerfavorite.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class MovieColumns implements BaseColumns {


        public static final String TABLE_MOVIE = "movie";
        public static String MOVIE_ID = "movie_id";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String POSTER = "poster";
    }

    public static final class TvColumns implements BaseColumns {

        public static final String TABLE_TV = "tv";
        public static String TV_ID = "tv_id";
        public static String TITLE_TV = "title_tv";
        public static String OVERVIEW_TV = "overview_tv";
        public static String POSTER_TV = "poster_tv";
    }

    public static final String AUTHORITY = "com.example.moviecatalogue";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(MovieColumns.TABLE_MOVIE)
            .build();

    public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TvColumns.TABLE_TV)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
