package com.elihimas.shuffler

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.elihimas.shuffler.activities.ShufflerActivity
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShufflerActivityTest {

    @Rule
    @JvmField
    val shufflerActivityRule = IntentsTestRule(ShufflerActivity::class.java)

    private val progressBarResource = ProgressBarResource(shufflerActivityRule)
    private val shuffleResource = ShuffleMenuItemResource(shufflerActivityRule)

    @Before
    fun setup() {
        Intents.intending(CoreMatchers.not(IntentMatchers.isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        IdlingRegistry.getInstance().register(progressBarResource, shuffleResource)
    }

    @After
    fun cleanUp() {
        IdlingRegistry.getInstance().unregister(progressBarResource, shuffleResource)
    }

    @Test
    fun shuffleClick_success() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.elihimas.shuffler", appContext.packageName)

        onView(withId(R.id.menu_item_shuffle))
            .perform(click())
            .perform(click())
            .perform(click())
            .perform(click())
            .perform(click())
            .perform(click())
            .perform(click())
            .perform(click())
    }

}
