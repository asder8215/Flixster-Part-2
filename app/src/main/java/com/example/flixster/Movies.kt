package com.example.flixster

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MoviesResponse(
    @SerialName("results")
    val response: List<Movies>
)

@Keep
@Serializable
data class Movies(
    @SerialName("poster_path")
    val movieImageUrl: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("overview")
    val description: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("popularity")
    val popularity: String?,
    @SerialName("vote_count")
    val voteCount: String?
) : java.io.Serializable

