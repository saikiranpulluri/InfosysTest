package com.example.test.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AboutDatabaseTest {

    private lateinit var aboutDao: AboutDao
    private lateinit var db: AboutDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AboutDatabase::class.java
        ).allowMainThreadQueries().build()
        aboutDao = db.aboutDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val about =
            AboutDatabaseModel("About India", "Tiger is national animal", "")
        aboutDao.insert(about)
        val byName = aboutDao.findRow(about.title)
        assertEquals(byName.title, about.title)
    }
}