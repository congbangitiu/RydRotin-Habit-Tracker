package com.project.rydrotin.ui.home

import android.content.Intent
import android.os.Looper
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.rydrotin.R
import com.project.rydrotin.ui.home.HomeActivity
import com.project.rydrotin.ui.habits.HabitsActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class HomeActivityTest {

    private lateinit var activity: HomeActivity

    @Before
    fun setup() {
        // Ensure the main looper is prepared
        if (Looper.getMainLooper() == null) {
            Looper.prepareMainLooper()
        }
        activity = Robolectric.buildActivity(HomeActivity::class.java).create().start().resume().get()
    }

    @Test
    fun clickingHabitCard_shouldStartHabitActivity() {
        val recyclerView = activity.findViewById<RecyclerView>(R.id.cards_container)

        val cardView = recyclerView.findViewHolderForAdapterPosition(0)?.itemView as? CardView

        assertNotNull("CardView not found", cardView)

        cardView?.performClick()

        ShadowLooper.runUiThreadTasks()
        ShadowLooper.idleMainLooper()

        val actualIntent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        assertNotNull("Intent should not be null", actualIntent)

        val expectedIntent = Intent(activity, HabitsActivity::class.java)
        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun clickingHistoryCard_shouldStartHistoryActivity() {

        val recyclerView = activity.findViewById<RecyclerView>(R.id.cards_container)

        val cardView = recyclerView.findViewHolderForAdapterPosition(1)?.itemView as? CardView

        assertNotNull("CardView not found", cardView)

        cardView?.performClick()

        ShadowLooper.runUiThreadTasks()
        ShadowLooper.idleMainLooper()

        val actualIntent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        assertNotNull("Intent should not be null", actualIntent)

        val expectedIntent = Intent(activity, HabitsActivity::class.java)
        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun clickingMoodCard_shouldStartMoodActivity() {

        val recyclerView = activity.findViewById<RecyclerView>(R.id.cards_container)

        val cardView = recyclerView.findViewHolderForAdapterPosition(2)?.itemView as? CardView

        assertNotNull("CardView not found", cardView)

        cardView?.performClick()

        ShadowLooper.runUiThreadTasks()
        ShadowLooper.idleMainLooper()

        val actualIntent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        assertNotNull("Intent should not be null", actualIntent)

        val expectedIntent = Intent(activity, HabitsActivity::class.java)
        assertEquals(expectedIntent.component, actualIntent.component)
    }
}