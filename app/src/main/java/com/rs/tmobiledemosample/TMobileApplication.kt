package com.rs.tmobiledemosample

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

//Create singleton instance in datastore preferences

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name="tmolocalstorage")
@HiltAndroidApp
class TMobileApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }

}