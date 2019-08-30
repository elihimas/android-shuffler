package com.elihimas.shuffler.adapters

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class LruImageLoader(context: Context) : ImageLoader(
    Volley.newRequestQueue(context),
    LruImageCache()
)

class LruImageCache : ImageLoader.ImageCache {
    private val cache = LruCache<String, Bitmap>(10)

    override fun getBitmap(url: String?) = cache[url]

    override fun putBitmap(url: String?, bitmap: Bitmap?) {
        cache.put(url, bitmap)
    }

}
