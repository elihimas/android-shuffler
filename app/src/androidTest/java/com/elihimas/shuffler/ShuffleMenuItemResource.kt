package com.elihimas.shuffler

import android.view.View
import com.elihimas.shuffler.activities.SplashActivity
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.elihimas.shuffler.activities.ShufflerActivity
import kotlinx.android.synthetic.main.activity_shuffler.*

class ShuffleMenuItemResource(private val testRule: IntentsTestRule<ShufflerActivity>) :
    IdlingResource {

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName() = "ShuffleMenuItemResource"

    override fun isIdleNow() =
        (testRule.activity?.findViewById<View>(R.id.menu_item_shuffle) != null).also { isIdle ->
            if (isIdle) {
                resourceCallback?.onTransitionToIdle()
            }
        }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }
}