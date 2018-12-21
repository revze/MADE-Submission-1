package io.revze.searchmovie.view.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.revze.searchmovie.R;
import io.revze.searchmovie.adapter.MovieAdapter;
import io.revze.searchmovie.model.Movie;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private MainPresenter presenter;
    private Context context;
    private EditText edtSearch;
    private RecyclerView rvMovie;
    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter adapter;
    private LinearLayout layoutLoader;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        onAttachView();
        context = this;

        layoutLoader = findViewById(R.id.layout_loader);
        edtSearch = findViewById(R.id.edt_search);
        Button btnSearch = findViewById(R.id.btn_search);
        rvMovie = findViewById(R.id.rv_movie);
        adapter = new MovieAdapter(context);

        btnSearch.setOnClickListener(this);
        rvMovie.setLayoutManager(new LinearLayoutManager(context));
        adapter.setMovies(movies);
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                presenter.searchMovie(context, edtSearch.getText().toString());
                break;
        }
    }

    @Override
    public void onFailedGetMovie(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessGetMovie(List<Movie> movies) {
        rvMovie.setVisibility(View.VISIBLE);
        this.movies.clear();
        this.movies.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoader() {
        rvMovie.setVisibility(View.GONE);
        layoutLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        rvMovie.setVisibility(View.VISIBLE);
        layoutLoader.setVisibility(View.GONE);
    }
}
