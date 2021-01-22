package com.example.test.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.test.database.AboutDatabase
import com.example.test.database.AboutDatabaseModel
import com.example.test.database.asDomainModel
import com.example.test.network.AboutNetwork
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository(private val database: AboutDatabase) {
    val aboutList: MutableLiveData<List<AboutDatabaseModel>> =
        Transformations.map(database.aboutDao.getAboutList()) {
            it.asDomainModel()
        } as MutableLiveData<List<AboutDatabaseModel>>

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh about is called")
            val playlist = AboutNetwork.aboutService.getAboutList()
            database.aboutDao.insertAll(playlist.asDatabaseModel())
        }
    }
}