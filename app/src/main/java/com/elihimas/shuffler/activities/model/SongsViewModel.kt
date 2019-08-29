package com.elihimas.shuffler.activities.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elihimas.shuffler.activities.api.SongsLoader

class SongsViewModel : ViewModel() {

    val songs = MutableLiveData<SongsListState>()

    fun loadData(context: Context) {
        val loader = SongsLoader(context)
        loader.load { songsList ->
            songs.value = songsList
        }
    }
}
