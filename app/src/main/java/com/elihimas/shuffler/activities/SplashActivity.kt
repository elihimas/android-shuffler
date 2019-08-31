package com.elihimas.shuffler.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.elihimas.shuffler.R

class SplashActivity : AppCompatActivity() {

    private companion object {
        const val REDIRECT_DELAY = 3000L
    }

    private val handler = Handler()
    private val redirectCallback = Runnable {
        ShufflerActivity.start(this)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed(redirectCallback, REDIRECT_DELAY)
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(redirectCallback)
    }
}
