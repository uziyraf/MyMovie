package com.dicoding.mymovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onItemClick)
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movie_title)
        private val overview: TextView = itemView.findViewById(R.id.movie_description)
        private val image: ImageView = itemView.findViewById(R.id.movie_image)
        private val player: TextView = itemView.findViewById(R.id.movie_players)
        private val releaseYear: TextView = itemView.findViewById(R.id.movie_year)

        fun bind(movie: Movie, onItemClick: (Movie) -> Unit) {
            title.text = movie.title
            overview.text = movie.description
            image.setImageResource(movie.photo)
            player.text = movie.players
            releaseYear.text = movie.releaseYear

            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}
