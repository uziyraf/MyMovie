package com.dicoding.mymovie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recMovie: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recMovie = findViewById(R.id.rec_movie)
        recMovie.setHasFixedSize(true)

        list.addAll(getListMovie())

        showRecyclerList()
        Log.d("MainActivity", "onCreate called")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListMovie(): ArrayList<Movie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPlayer = resources.getStringArray(R.array.data_player)
        val dataReleaseYear = resources.getStringArray(R.array.data_release_year)

        val listMovie = ArrayList<Movie>()
        for (i in dataName.indices) {
            val photoId = dataPhoto.getResourceId(i, -1)

            if (photoId == -1) {
                Log.e("MainActivity", "Error: Resource ID for index $i is invalid!")
            }else { val movie = Movie(
                title = dataName[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1),
                players = dataPlayer[i],
                releaseYear = dataReleaseYear[i].toString())
                listMovie.add(movie)
            }
        }
        dataPhoto.recycle()
        return listMovie
    }

    private fun showRecyclerList() {
        recMovie.layoutManager = LinearLayoutManager(this)
        val movieAdapter = MovieAdapter(list) { movie ->
            Log.d("MainActivity", "Movie clicked: ${movie.title}")
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("key_movie", movie)
            }
            startActivity(intent)
        }
        recMovie.adapter = movieAdapter
    }
}
