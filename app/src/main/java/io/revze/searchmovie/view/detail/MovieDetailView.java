package io.revze.searchmovie.view.detail;

import java.util.List;

import io.revze.searchmovie.model.Movie;
import io.revze.searchmovie.model.MovieDetailResponse;
import io.revze.searchmovie.view.base.View;

public interface MovieDetailView extends View {
    void showLoader();

    void hideLoader();

    void onSuccessGetDetail(MovieDetailResponse response);

    void onFailedGetDetail(String message);
}
