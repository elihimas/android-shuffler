package com.elihimas.shuffler.activities.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elihimas.shuffler.model.Song

class SongsViewModel : ViewModel() {

    val songs = MutableLiveData<List<Song>>()
}
