package com.example.myapplicationb.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationb.R
import com.example.myapplicationb.data.MovieDetails
import com.example.myapplicationb.viewmodel.BooksViewModel
import com.squareup.picasso.Picasso

class BooksDetailsActivity() : AppCompatActivity() {
    private val mViewModel: BooksViewModel = BooksViewModel()

    private lateinit var mTitle: TextView
    private lateinit var mReleaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView
    private lateinit var mBanner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_details)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        mViewModel.getActors()
        mViewModel.getMovieDetails(id)
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@BooksDetailsActivity) {
                setMovieInformation(it)
            }
        }
    }

    private fun setMovieInformation(movieDetails: MovieDetails?){
        mTitle.text = movieDetails?.title
        mReleaseDate.text = movieDetails?.release_date.toString()
        mScore.text = movieDetails?.vote_average.toString()
        mOverview.text = movieDetails?.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetails?.backdrop_path).into(mBanner)
    }

    private fun initViews() {
        mTitle = findViewById(R.id.books_details_title)
        mReleaseDate = findViewById(R.id.books_details_date)
        mScore = findViewById(R.id.books_details_score)
        mOverview = findViewById(R.id.books_details_body_overview)
        mBanner = findViewById(R.id.books_details_image_banner)
    }
}