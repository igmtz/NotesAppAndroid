package com.gmtz.notesappandroid.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {
    companion object {
        private val Context.datastore : DataStore<Preferences> by preferencesDataStore("notesAppPreferences")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val COLOR_BLIND_MODE = booleanPreferencesKey("color_blind_mode")
    }

    val getDarkMode: Flow<Boolean> = context.datastore.data
        .map { it[DARK_MODE] ?: false }

    suspend fun updateDarkMode(value: Boolean) {
        context.datastore.edit { it[DARK_MODE] = value }
    }

    val getColorBlindMode: Flow<Boolean> = context.datastore.data
        .map { it[COLOR_BLIND_MODE] ?: false }

    suspend fun updateColorBlindMode(value: Boolean) {
        context.datastore.edit { it[COLOR_BLIND_MODE] = value }
    }
}