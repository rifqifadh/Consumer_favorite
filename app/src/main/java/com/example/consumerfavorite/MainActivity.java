package com.example.consumerfavorite;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.consumerfavorite.adapter.TabAdapter;
import com.example.consumerfavorite.feature.MovieFragment;
import com.example.consumerfavorite.feature.TvFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setUpViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new MovieFragment(), "Movie");
        tabAdapter.addFragment(new TvFragment(), "TV");

        viewPager.setAdapter(tabAdapter);
    }
}
