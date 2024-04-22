package ru.tk4dmitriy.data.departure_place.impl.sharedPref

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val SHARED_PREF_NAME = "AirfaresPref"
private const val DEPARTURE_PLACE_KEY = "DEPARTURE_PLACE_KEY"

class DeparturePlaceSharedPrefImpl @Inject constructor(
    private val context: Context
) : DeparturePlaceSharedPref {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }
    override suspend fun saveDeparturePlace(departurePlace: String) = withContext(Dispatchers.IO) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(DEPARTURE_PLACE_KEY, departurePlace)
        editor.apply()
    }

    override suspend fun getDeparturePlace(): String = withContext(Dispatchers.IO) {
        sharedPref.getString(DEPARTURE_PLACE_KEY, "") ?: ""
    }
}