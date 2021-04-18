package com.dhgrupo5.popfilm.pack.utils.moviesdb

import com.dhgrupo5.popfilm.pack.model.*
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("authentication/token/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getRequestToken(): RequestToken

    @GET("authentication/guest_session/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGuestSession() : GuestSession

    @GET("genre/movie/list?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGenre() : GenresResponse

    @GET("movie/{movie_id}?api_key=${NetworkUtils.API_KEY}")
    suspend fun getMovieID() : MovieResponse

//    @GET("movie/{id}/images?api_key=${NetworkUtils.API_KEY}")
//    suspend fun getMovieMedia(@Path("id") movieId: ImageResponse)

    @GET("configuration")
    suspend fun getMoviesConfiguration() : MovieDetail

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("language") language: String?
    ): GenresResponse


    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("language") language: String?,
        @Query("with_genres") genre:String?
    ):DiscoverResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String?
    ):Movie


    @GET("discover/movie?api_key=${NetworkUtils.API_KEY}")
    suspend fun discoverMovies(
        @Query("language") language: String = "pt-BR",
        @Query("region") region: String = "BR",
        @Query("sort_by") sortBy: String = "",
        @Query("certification_country") certCountry: String = "",
        @Query("certification") certification: String = "",
        @Query("certification.lte") certMax: String = "",
        @Query("certification.gte") certMin: String = "",
        @Query("include_adult") includeAdult: String = "",
        @Query("include_video") includeVideo: String = "",
        @Query("page") page: String = "1",
        @Query("primary_release_year") releaseYear: String = "",
        @Query("primary_release_date.lte") releaseYearMin: String = "",
        @Query("primary_release_date.gte") releaseYearMax: String = "",
        @Query("release_date.lte") releaseDateMin: String = "",
        @Query("release_date.gte") releaseDateMax: String = "",
        @Query("with_release_type") releaseType: String = "",
        @Query("year") year: String = "",
        @Query("vote_count.lte") votesMin: String = "",
        @Query("vote_count.gte") votesMax: String = "",
        @Query("vote_average.lte") ratingMin: String = "",
        @Query("vote_average.gte") ratingMax: String = "",
        @Query("with_cast") cast: String = "",
        @Query("with_crew") crew: String = "",
        @Query("with_people") people: String = "",
        @Query("with_companies") companies: String = "",
        @Query("with_genres") genresInclude: List<Genre>,
        @Query("without_genres") genresExclude: String = "",
        @Query("with_keywords") keywordsInclude: String = "",
        @Query("without_keywords") keywordsExclude: String = "",
        @Query("with_runtime.lte") runtimeMin: String = "",
        @Query("with_runtime.gte") runtimeMax: String = "",
        @Query("with_original_language") originalLanguage: String = "",
        @Query("with_watch_providers") watchProviders: String = "",
        @Query("watch_region") watchRegion: String = "",
        @Query("with_watch_monetization_types") monetizationTypes: String = "",
    ): DiscoverResponse


}
