package io.revze.searchmovie.api;

import io.reactivex.Observable;
import io.revze.searchmovie.model.MovieDetailResponse;
import io.revze.searchmovie.model.MovieResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Observable<MovieResponse> search(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("query") String query);

    @GET("movie/{id}")
    Observable<MovieDetailResponse> getMovieDetail(@Path("id") int id,
                                                   @Query("api_key") String apiKey);
}
