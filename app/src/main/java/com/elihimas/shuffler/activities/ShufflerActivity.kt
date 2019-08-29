package com.elihimas.shuffler.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.VolleyError
import com.elihimas.shuffler.R
import com.elihimas.shuffler.activities.model.SongsListState
import com.elihimas.shuffler.activities.model.SongsViewModel
import com.elihimas.shuffler.model.Song
import kotlinx.android.synthetic.main.activity_shuffler.*

class ShufflerActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ShufflerActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuffler)

        init()
    }

    private fun init() {
        val songsObserver = Observer<SongsListState> { songs -> onSongsChanged(songs) }
        val model = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        model.songs.observe(this, songsObserver)

        showLoading()
        model.loadData(this)
    }

    fun showLoading() {
        items_recycler.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        items_recycler.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    private fun onSongsChanged(songsListState: SongsListState) {
        hideLoading()

        val songs = songsListState.songs
        if (songs != null) {
            showSongs(songs)
        } else {
            songsListState.error?.let { error ->
                showError(error)
            }
        }
    }

    private fun showSongs(songs: List<Song>) {

    }

    private fun showError(error: VolleyError) {

    }
}
