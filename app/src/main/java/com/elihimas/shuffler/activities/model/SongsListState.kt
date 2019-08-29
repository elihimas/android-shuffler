package com.elihimas.shuffler.activities.model

import com.android.volley.VolleyError
import com.elihimas.shuffler.model.Song

class SongsListState(val songs: List<Song>?, val error: VolleyError?) {

    constructor (songs: List<Song>?) : this(songs, null)
    constructor(error: VolleyError?) : this(null, error)

}
