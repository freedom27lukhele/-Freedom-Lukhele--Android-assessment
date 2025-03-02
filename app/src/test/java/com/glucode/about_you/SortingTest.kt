package com.glucode.about_you

import android.view.MenuItem
import androidx.test.core.app.ApplicationProvider
import com.glucode.about_you.R
import com.glucode.about_you.engineers.EngineersFragment
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class EngineersFragmentTest {

    private lateinit var fragment: EngineersFragment
    private lateinit var mockEngineers: List<Engineer>

    @Before
    fun setUp() {
        fragment = EngineersFragment()

        mockEngineers = listOf(
            Engineer("Freedom", "Android Developer", "", QuickStats(12,222,322),),  // 5 years
            Engineer("Freedom", "Typescript Developer", "", QuickStats(10,234,222),),  // 5 years
            Engineer("Freedom", "JAva Developer", "", QuickStats(20,233,233),),  // 5 years
             )

        fragment.setUpEngineersList(mockEngineers)
    }

    @Test
    fun `sorting engineers by years should be in ascending order`() {
        val menuItem = mock(MenuItem::class.java)
        `when`(menuItem.itemId).thenReturn(R.id.action_years)

        fragment.onOptionsItemSelected(menuItem)

        val sortedList = mockEngineers.sortedBy { it.quickStats.years }
        assertEquals(sortedList[0].name, "freedom")
        assertEquals(sortedList[1].name, "manqoba")
        assertEquals(sortedList[2].name, "sbu")
    }

    @Test
    fun `sorting engineers by coffees should be in ascending order`() {
        val menuItem = mock(MenuItem::class.java)
        `when`(menuItem.itemId).thenReturn(R.id.action_coffees)

        fragment.onOptionsItemSelected(menuItem)

        val sortedList = mockEngineers.sortedBy { it.quickStats.coffees }
        assertEquals(sortedList[0].name, "freedom")
        assertEquals(sortedList[1].name, "manqoba")
        assertEquals(sortedList[2].name, "sbu")
    }

    @Test
    fun `sorting engineers by bugs should be in ascending order`() {
        val menuItem = mock(MenuItem::class.java)
        `when`(menuItem.itemId).thenReturn(R.id.action_bugs)

        fragment.onOptionsItemSelected(menuItem)

        val sortedList = mockEngineers.sortedBy { it.quickStats.bugs }
        assertEquals(sortedList[0].name, "sbu")
        assertEquals(sortedList[1].name, "manqoba")
        assertEquals(sortedList[2].name, "freedom")
    }
}
