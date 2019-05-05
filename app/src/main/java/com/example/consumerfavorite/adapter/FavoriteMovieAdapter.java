package com.example.consumerfavorite.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.consumerfavorite.feature.MovieFragment;
import com.example.consumerfavorite.R;
import com.example.consumerfavorite.entity.MovieItem;
import com.example.consumerfavorite.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {

    private Cursor cursor;
    private MovieFragment movieFragment;

    public void setListMovies(Cursor listMovies) {
        this.cursor = listMovies;
    }

    public FavoriteMovieAdapter(MovieFragment movieFragment, Cursor list) {
        this.movieFragment = movieFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final MovieItem movieItem = getItem(position);

        viewHolder.tvTitle.setText(movieItem.getTitle());
        viewHolder.tvDesc.setText(movieItem.getOverview());
        Glide.with(movieFragment).load(ApiClient.IMAGE_BASE_URL + movieItem.getPosterPath()).into(viewHolder.imgPoster);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    private MovieItem getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new MovieItem(cursor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvTitle;

        @BindView(R.id.tv_desc)
        TextView tvDesc;

        @BindView(R.id.iv_poster)
        ImageView imgPoster;

        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
