package com.elihimas.shuffler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elihimas.shuffler.R
import com.elihimas.shuffler.model.Song
import kotlinx.android.synthetic.main.song_item.view.*


class SongsAdapter(context: Context) : RecyclerView.Adapter<SongHolder>() {

    private var songs = listOf<Song>()
    private var imageLoader = LruImageLoader(context)

    fun updateList(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SongHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        )

    override fun getItemCount() = songs.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(songs[position], imageLoader)
    }

}

class SongHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(song: Song, imageLoader: LruImageLoader) {
        view.artwork_image.setImageUrl(song.artworkUrl, imageLoader)
        view.song_name_text.text = song.trackName
        view.song_info_text.text = "${song.artistName} (${song.genreName})"
    }

}
