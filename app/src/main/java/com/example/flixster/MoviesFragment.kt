package com.example.flixster
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "d63c4073d0fb01483769c901b379b75e"

class MoviesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ProgressBar, recyclerView: RecyclerView) {
        progressBar.visibility = View.VISIBLE

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        val url = "https://api.themoviedb.org/3/movie/now_playing"

        // Using the client, perform the HTTP request
        client[url, params, object : JsonHttpResponseHandler(){
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.visibility = View.GONE

                        val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray

                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<Movies>>() {}.type

                        val models : List<Movies> = gson.fromJson(resultsJSON.toString(), arrayMovieType)
                        recyclerView.adapter = MoviesAdapter(models)

                        // Look for this in Logcat:
                        Log.d("MoviesFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.visibility = View.GONE

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MoviesFragment", errorResponse)
                        }
                    }
                }]

    }

}