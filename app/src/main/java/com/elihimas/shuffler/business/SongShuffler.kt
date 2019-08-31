package com.elihimas.shuffler.business

import com.elihimas.shuffler.model.Song

object SongShuffler {

    fun shuffle(songs: List<Song>): List<Song> {
        val shuffledSongs = mutableListOf<Song>()
        val artistsSongsMap = songs.groupByTo(mutableMapOf()) { song -> song.artistName }

        while (artistsSongsMap.keys.isNotEmpty()) {
            val artists = artistsSongsMap.keys.toMutableList()

            while (artists.isNotEmpty()) {
                val pickedArtistIndex = random(artists.size)
                val pickedArtist = artists.removeAt(pickedArtistIndex)

                val pickedArtistSongs = artistsSongsMap.getValue(pickedArtist)
                val pickedSongIndex = random(pickedArtistSongs.size)
                val pickedSong = pickedArtistSongs.removeAt(pickedSongIndex)

                shuffledSongs.add(pickedSong)

                if (pickedArtistSongs.isEmpty()) {
                    artistsSongsMap.remove(pickedArtist)
                }
            }
        }

        return shuffledSongs
    }

    private fun random(maxIndex: Int) = (Math.random() * maxIndex).toInt()

}
