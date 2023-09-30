package com.dzakyadlh.githubuser.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dzakyadlh.githubuser.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(SearchActivity::class.java)
    }

    @Test
    fun searchComponentDisplayed() {
        Thread.sleep(2000)
        onView(withId(R.id.searchBar)).check(matches(isDisplayed()))
        onView(withId(R.id.searchResults)).check(matches(isDisplayed()))
    }

    @Test
    fun searchToDetailComponentDisplayed() {
        Thread.sleep(2000)
        onView(withId(R.id.searchBar)).check(matches(isDisplayed()))
        onView(withId(R.id.searchResults)).check(matches(isDisplayed()))
        onView(withId(R.id.searchResults)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.detail_linear_layout)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.detail_profile_pic)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_profile_username)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_profile_name)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_profile_bio)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_view_pager)).check(matches(isDisplayed()))
    }
}