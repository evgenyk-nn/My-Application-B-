package com.example.myapplicationb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.SyncStateContract
import android.widget.MediaController
import android.util.Log
import android.widget.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksDetailsActivity() : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_details)

        val id = intent.getIntExtra("id", 0)
        title = findViewById(R.id.books_details_title)
        releaseDate = findViewById(R.id.books_details_date)
        score = findViewById(R.id.books_details_score)
        overview = findViewById(R.id.books_details_body_overview)
        banner = findViewById(R.id.books_details_image_banner)

        val apiInterface = id?.let { ApiInterface.create().getMovieDetails(it, Constants.API_KEY) }
        apiInterface?.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>) {
                title.text = response?.body()?.title
                releaseDate.text = response?.body()?.release_date.toString()
                score.text = response?.body()?.vote_average.toString()
                overview.text = response?.body()?.overview


                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path)
                    .into(banner)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testlogs", "API call failed: ${t.message}")
            }
        })
    }
}




//class BooksDetailsActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_books_details)
//        val id = intent.getIntExtra("id", 0)
//        Log.d("testing","id $id")
//    }
//}