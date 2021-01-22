package com.example.test.viewmodels

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.database.AboutDatabaseModel
import com.example.test.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        // When adding a new task
        val about = AboutDatabaseModel("About Android", "Most Used OS", "")
        mainViewModel.aboutList.value = listOf(about)

        // Then the new task event is triggered
        val value = mainViewModel.aboutList.getOrAwaitValue()

        MatcherAssert.assertThat(
            value[0].title,
            CoreMatchers.`is`(about.title)
        )
    }
}