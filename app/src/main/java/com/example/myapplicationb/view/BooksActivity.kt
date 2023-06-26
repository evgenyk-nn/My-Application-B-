package com.example.myapplicationb.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationb.view.adapters.CustomAdapter
import com.example.myapplicationb.*
import com.example.myapplicationb.viewmodel.BooksViewModel

@Suppress("DEPRECATION")
class BooksActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val mViewModel: BooksViewModel = BooksViewModel()
    private val mMoviesViewModel:BooksViewModel = BooksViewModel()


    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        initViews()
        initObserves()
        mViewModel.getMovies()
        mMoviesViewModel.getActors()
    }

    private fun initObserves() {
        mViewModel.apply {
            movies.observe(this@BooksActivity) {
                mMoviesAdapter = CustomAdapter(it, this@BooksActivity)
                mMoviesRecycler.adapter = mMoviesAdapter
            }
        }
    }

    private fun initViews() {
        mMoviesRecycler = findViewById<RecyclerView>(R.id.recyclerview)
        mMoviesRecycler.layoutManager = GridLayoutManager(this, 2) // Укажите необходимое количество столбцов
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@BooksActivity, BooksDetailsActivity::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }
}