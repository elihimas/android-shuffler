package com.elihimas.shuffler

import com.elihimas.shuffler.business.SongShuffler
import com.elihimas.shuffler.model.Song
import org.junit.Test

import org.junit.Assert.*

class ShufflerTest {

    private companion object {
        const val RUN_COUNT = 100
    }

    @Test
    fun shuffle_success() {
        fun verify(result: List<Song>) {
            for (index in 1 until result.size) {
                val previousSong = result[index - 1]
                val currentSong = result[index]

                assertNotEquals(previousSong.artistName, currentSong.artistName)
            }
        }

        val songs = mockSongs()

        for (run in 0..RUN_COUNT) {
            val result = SongShuffler.shuffle(songs)
            verify(result)
        }

    }

    private fun mockSongs() =
        listOf(
            Song("Samba em prelúdio", "Vinicius de Moraes", "url1", "bossa nova"),
            Song("O Pato", "Vinicius de Moraes", "url2", "bossa nova"),
            Song("Eu sei que vou te amar", "Vinicius de Moraes", "url3", "bossa nova"),
            Song("Pedro Pedreiro", "Chico Buarque", "url4", "mbp"),
            Song("Agora Falando Sério", "Chico Buarque", "url5", "mbp"),
            Song("Mano a Mano", "Chico Buarque", "url6", "mbp"),
            Song("Carmen", "Stromae", "url7", "Hip Hop"),
            Song("Formidable", "Stromae", "url8", "Hip Hop"),
            Song("Tous Les Mêmes", "Stromae", "url9", "Hip Hop")
        )
}
