package com.example.myapplicationb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplicationb.BooksDBRepositoryImpl
import com.example.myapplicationb.data.MovieDetails
import com.example.myapplicationb.data.Movies
import com.example.myapplicationb.data.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BooksViewModel {

    private val _movies: MutableLiveData<List<Result?>> = MutableLiveData()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _moviesRepository: BooksDBRepositoryImpl = BooksDBRepositoryImpl()

    fun getMovies() {
        val response = _moviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                Log.d("testLogs", "OnResponse Success ${call.toString()} ")
                _movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("testLogs", "OnFailure: ${t.message}")
            }
        })
    }

    fun getMovieDetails(id: Int) {
        val response = _moviesRepository.getMovieDetails(id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("testLogs", "OnResponse Success ${call.toString()} ")
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testLogs", "OnFailure: ${t.message}")
            }
        })
    }

    fun getActors() {
        val response = _moviesRepository.getActors()
        // TODO: Implement getting actors
    }
}

private fun String.enqueue(callback: Callback<Movies>) {
    TODO("Not yet implemented")
}
