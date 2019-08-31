package com.elihimas.shuffler.business

import com.elihimas.shuffler.model.Song

object SongShuffler {

    fun shuffle(songs: List<Song>): List<Song> {
        val shuffledSongs = mutableListOf<Song>()
        val artistsSongsMap = songs.groupByTo(mutableMapOf()) { song -> song.artistName }

        while (artistsSongsMap.keys.isNotEmpty()) {
            val artists = artistsSongsMap.keys.toMutableList()

            while (artists.isNotEmpty()) {
                val lastPickedArtist = shuffledSongs.lastOrNull()?.artistName
                val pickedArtist = pickAndRemoveArtist(artists, lastPickedArtist)

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

    private fun pickAndRemoveArtist(
        artists: MutableList<String>,
        lastPickedArtist: String?
    ): String {
        var pickedArtistIndex = random(artists.size).let { randomIndex ->
            if (artists[randomIndex] == lastPickedArtist) {
                (randomIndex + 1) % artists.size
            } else {
                randomIndex
            }

        }
        val pickedArtist = artists.removeAt(pickedArtistIndex)

        return pickedArtist
    }

    private fun random(maxIndex: Int) = (Math.random() * maxIndex).toInt()

}
