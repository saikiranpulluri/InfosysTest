package com.example.test.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AboutDao {
    @Query("select * from aboutdatabasemodel")
    fun getAboutList(): LiveData<List<AboutDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<AboutDatabaseModel>)

    @Query("DELETE FROM aboutdatabasemodel")
    fun deleteAll()
}

@Database(entities = [AboutDatabaseModel::class], version = 1)
abstract class AboutDatabase : RoomDatabase() {
    abstract val aboutDao: AboutDao
}

private lateinit var INSTANCE: AboutDatabase
fun getDatabase(context: Context): AboutDatabase {
    synchronized(AboutDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AboutDatabase::class.java,
                "aboutDb"
            ).build()
        }
    }
    return INSTANCE
}