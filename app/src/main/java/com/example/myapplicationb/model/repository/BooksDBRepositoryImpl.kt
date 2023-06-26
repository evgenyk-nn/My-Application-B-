package com.example.myapplicationb

import com.example.myapplicationb.data.MovieDetails
import com.example.myapplicationb.data.Movies
import com.example.myapplicationb.model.apis.ApiInterface
import com.example.myapplicationb.model.repository.BooksDBRepository
import retrofit2.Call

class BooksDBRepositoryImpl : BooksDBRepository {
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "en-US", 1)
    }

    override fun getMovieDetails(id: Int, apiKey: String): Call<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, Constants.API_KEY)
    }

    override fun getActors(): Call<Movies> {
        return apiInterface.getActors()
    }
}
