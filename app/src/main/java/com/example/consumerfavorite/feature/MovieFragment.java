package com.example.consumerfavorite.feature;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.consumerfavorite.R;
import com.example.consumerfavorite.adapter.FavoriteMovieAdapter;

import static com.example.consumerfavorite.database.DatabaseContract.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private FavoriteMovieAdapter favoriteMovieAdapter;
    private Cursor list;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie, container, false);
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh_movie);
        recyclerView = view.findViewById(R.id.rv_list_fav_movie);

        new loadData().execute();
        showListData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new loadData().execute();
                showListData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void showListData() {
        favoriteMovieAdapter = new FavoriteMovieAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favoriteMovieAdapter);
        favoriteMovieAdapter.setListMovies(list);
        recyclerView.setHasFixedSize(true);
    }

    private class loadData extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            list = cursor;
            favoriteMovieAdapter.setListMovies(list);
            favoriteMovieAdapter.notifyDataSetChanged();
        }
    }

}
