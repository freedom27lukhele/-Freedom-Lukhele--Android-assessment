package com.glucode.about_you

import android.view.MenuItem
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glucode.about_you.engineers.EngineersFragment
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats
import com.glucode.about_you.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class EngineersFragmentTest {

    private lateinit var fragment: EngineersFragment
    private lateinit var mockEngineers: List<Engineer>
    @Before
    fun setUp() {

        fragment = EngineersFragment()
        mockEngineers = mutableListOf(
            Engineer("freedom", "Android Developer", "", QuickStats(12, 222, 322)),
            Engineer("manqoba", "Typescript Developer", "", QuickStats(10, 234, 222)),
            Engineer("sbu", "Java Developer", "", QuickStats(20, 233, 233)),
        )
    }

    @Test
    fun `sorting engineers by years should be in ascending order`() {
        val scenario = launchFragmentInContainer<EngineersFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment { fragment ->
            val menuItem = mock(MenuItem::class.java)
            `when`(menuItem.itemId).thenReturn(R.id.action_years)

            fragment.onOptionsItemSelected(menuItem)

            val sortedList = mockEngineers.sortedBy { it.quickStats.years }
            assertEquals(sortedList[0].name, "manqoba")
            assertEquals(sortedList[1].name, "freedom")
            assertEquals(sortedList[2].name, "sbu")
        }
    }

    @Test
    fun `sorting engineers by coffees should be in ascending order`() {
        val scenario = launchFragmentInContainer<EngineersFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment { fragment ->
            val menuItem = mock(MenuItem::class.java)
            `when`(menuItem.itemId).thenReturn(R.id.action_coffees)

            fragment.onOptionsItemSelected(menuItem)

            val sortedList = mockEngineers.sortedBy { it.quickStats.coffees }
            assertEquals(sortedList[0].name, "freedom")
            assertEquals(sortedList[1].name, "manqoba")
            assertEquals(sortedList[2].name, "sbu")
        }
    }

    @Test
    fun `sorting engineers by bugs should be in ascending order`() {
        val scenario = launchFragmentInContainer<EngineersFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment { fragment ->
            val menuItem = mock(MenuItem::class.java)
            `when`(menuItem.itemId).thenReturn(R.id.action_bugs)

            fragment.onOptionsItemSelected(menuItem)

            val sortedList = mockEngineers.sortedBy { it.quickStats.bugs }
            assertEquals(sortedList[0].name, "sbu")
            assertEquals(sortedList[1].name, "manqoba")
            assertEquals(sortedList[2].name, "freedom")
        }
    }
}
