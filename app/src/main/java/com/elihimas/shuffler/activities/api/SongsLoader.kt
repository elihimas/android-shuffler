package com.elihimas.shuffler.activities.api

import android.content.Context
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.elihimas.shuffler.model.Song
import com.android.volley.Request
import com.android.volley.VolleyError
import com.elihimas.shuffler.R
import com.elihimas.shuffler.activities.model.SongsListState
import com.google.gson.Gson

class SongsLoader(val context: Context) {

    private companion object {
        const val BASE_URL =
            "https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id=%s"
    }

    private val queue = Volley.newRequestQueue(context)

    fun load(loadCallback: (SongsListState) -> Unit) {
        val currentSongs = mutableListOf<Song>()

        fun stringToSongs(songsString: String): List<Song> {
            val gson = Gson()
            val response = gson.fromJson(songsString, LookupResponse::class.java)
            val songsWithoutFirstItem = response.results.subList(1, response.results.size)

            return songsWithoutFirstItem
        }

        fun loadSuccess(response: String) {
            val songs = stringToSongs(response)

            currentSongs.addAll(songs)

            loadCallback(SongsListState(currentSongs))
        }

        fun loadError(error: VolleyError) {
            loadCallback(SongsListState(error))
        }

        val artistsIds = context.resources.getStringArray(R.array.artists_ids)
        artistsIds.forEach { id ->
            val url = String.format(BASE_URL, id)
            val request = StringRequest(Request.Method.GET, url, ::loadSuccess, ::loadError)
            queue.add(request)
        }
    }
}
