package com.elihimas.shuffler

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.elihimas.shuffler.activities.ShufflerActivity
import com.elihimas.shuffler.activities.api.SongsLoader
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsLoaderTest {

    @Rule
    @JvmField
    var shufflerActivityRule = IntentsTestRule<ShufflerActivity>(ShufflerActivity::class.java)

    @Before
    fun setup() {
        Intents.intending(CoreMatchers.not(IntentMatchers.isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    @Test
    fun load_success() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val loader = SongsLoader(appContext)
        loader.load { songsListState ->
            val error = songsListState.error
            val songs = songsListState.songs

            assertNull(error)
            assertNotNull(songs)
            assert(songsListState.songs!!.isNotEmpty())
        }
    }
}
