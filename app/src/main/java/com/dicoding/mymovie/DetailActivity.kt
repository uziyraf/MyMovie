package com.dicoding.mymovie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_detail_data)

        Log.d("DetailActivity", "Activity created")

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_movie")
        }
        if (movie != null) {
            try {
                // Menghubungkan TextView dan ImageView ke ID yang ada di layout
                val title: TextView = findViewById(R.id.movie_title)
                val image: ImageView = findViewById(R.id.movie_image)
                val description: TextView = findViewById(R.id.movie_description)
                val player: TextView = findViewById(R.id.movie_players)
                val releaseYear: TextView = findViewById(R.id.movie_year)

                // Mengisi data yang diterima ke dalam view
                title.text = movie.title
                image.setImageResource(movie.photo)
                description.text = movie.description
                player.text = getString(R.string.player_label, movie.players)
                releaseYear.text = getString(R.string.release_year_label, movie.releaseYear)
            } catch (e: Exception) {
                Log.e("DetailActivity", "Error while displaying movie details", e)
            }
        } else {
            Log.e("DetailActivity", "Movie data is null")  // Jika objek movie tidak ditemukan
        }
    }
}