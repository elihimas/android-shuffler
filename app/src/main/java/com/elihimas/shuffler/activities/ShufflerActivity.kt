package com.elihimas.shuffler.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.elihimas.shuffler.R
import com.elihimas.shuffler.activities.model.SongsViewModel
import com.elihimas.shuffler.model.Song

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

        val songsObserver = Observer<List<Song>> { songs -> onSongsChanged(songs) }
        val model = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        model.songs.observe(this, songsObserver)
    }

    private fun onSongsChanged(songs: List<Song>) {
        
    }
}
