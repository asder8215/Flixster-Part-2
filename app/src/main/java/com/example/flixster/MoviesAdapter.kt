package com.example.flixster
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id


const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"
/**
 * [RecyclerView.Adapter] that can display a [Movies]
 */
class MoviesAdapter(private val context: Context, private val movie: List<Movies>):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_items, parent, false)
            return MovieViewHolder(view)
        }

        /**
         * This inner class lets us refer to all the different View elements
         * (Yes, the same ones as in the XML layout files!)
         */
        inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView),
            View.OnClickListener {
            var mItem: Movies? = null
            val mMovieTitle: TextView = mView.findViewById<View>(id.title) as TextView
            val mMovieDescription: TextView = mView.findViewById<View>(id.description) as TextView
            val mMovieImage: ImageView = mView.findViewById<View>(id.movieImage) as ImageView

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val movies = movie[absoluteAdapterPosition]
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(MOVIE_EXTRA, movies)
                context.startActivity(intent)

            }


        }

        /**
         * This lets us "bind" each Views in the ViewHolder to its' actual data!
         */
        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movies = movie[position]

            holder.mItem = movies
            holder.mMovieTitle.text = movies.title
            holder.mMovieDescription.text = movies.description

            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500/" + movies.movieImageUrl)
                .centerInside()
                .into(holder.mMovieImage)
        }

        /**
         * Remember: RecyclerView adapters require a getItemCount() method.
         */
        override fun getItemCount(): Int {
            return movie.size
        }

}