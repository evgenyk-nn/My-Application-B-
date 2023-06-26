package com.example.myapplicationb.model.repository

import com.example.myapplicationb.data.MovieDetails
import com.example.myapplicationb.data.Movies
import retrofit2.Call

/**
 * Repository provides information taken from MoviesDB API
 */

interface BooksDBRepository {

    /**
     * Returns list of popular [Movies]
     */
    fun getMovies(): Call<Movies>
    /**
     * Returns information for a single movie by returning [MovieDetails]
     * @param id -identification number of the need movie
     * @param apikey - key for an api call to make a request
     */

    fun getMovieDetails(id: Int, apiKey: String): Call<MovieDetails>

    fun getMovieDetails(id: Int): Call<MovieDetails>
    fun getActors(): Call<Movies>
}



//package com.example.myapplicationb
//
//import com.example.myapplicationb.data.MovieDetails
//import com.example.myapplicationb.data.Movies
//import retrofit2.Call
//
//interface BooksDBRepository {
//
//    fun getMovies(): Call<Movies>
//
//    fun getMovieDetails(id: Int): Call<MovieDetails>
//
//    fun getActors(): Call<Movies>
//}