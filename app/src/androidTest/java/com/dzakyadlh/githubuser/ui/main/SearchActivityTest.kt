package com.dzakyadlh.githubuser.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest {
    private val dummyUsername1 = "dzakyadlh"
    private val dummyUsername2 = "dicoding"
    private val dummyUsername3 = "freeCodeCamp"

    @Before
    fun setup() {
        ActivityScenario.launch(SearchActivity::class.java)
    }

    @Test
    fun assertGetSearchResult() {
     
    }
}