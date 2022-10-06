package com.example.flixster

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


private const val TAG = "DetailActivity"

class DetailActivity: AppCompatActivity() {
    private lateinit var movieImage: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var popularity: TextView
    private lateinit var releaseDate: TextView
    private lateinit var voteCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieImage = findViewById<ImageView>(R.id.movieImage)
        title = findViewById<TextView>(R.id.title)
        description = findViewById<TextView>(R.id.description)
        popularity = findViewById<TextView>(R.id.popularity)
        releaseDate = findViewById<TextView>(R.id.releaseDate)
        voteCount = findViewById<TextView>(R.id.voteCount)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movies


        title.text = movie.title
        description.text = movie.description
        popularity.text = "Popularity (Out of a 100): " + movie.popularity
        releaseDate.text = "Release Date of Movie: " + movie.releaseDate
        voteCount.text = "The number of votes on this movie: " + movie.voteCount

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + movie.movieImageUrl)
            .into(movieImage)

    }
}