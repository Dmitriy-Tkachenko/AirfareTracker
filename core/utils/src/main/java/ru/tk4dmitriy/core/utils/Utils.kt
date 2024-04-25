package ru.tk4dmitriy.core.utils

import android.content.res.AssetManager
import android.icu.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {
    fun Int.formatNumberByDigits(): String {
        val numberString = toString()
        val reversed = numberString.reversed()
        val stringBuilder = StringBuilder()

        for ((index, char) in reversed.withIndex()) {
            if (index != 0 && index % 3 == 0) {
                stringBuilder.append(' ')
            }
            stringBuilder.append(char)
        }

        return stringBuilder.reverse().toString()
    }

    fun AssetManager.readJsonFromAssets(fileName: String): String =
        open(fileName).bufferedReader().use {
            it.readText()
        }

    fun AssetManager.readAssetAsByteArray(assetName: String): ByteArray =
        open(assetName).use { inputStream ->
            return inputStream.readBytes()
        }

    fun String.filterText(): String {
        val regex = Regex("[^а-яёА-ЯЁ\\s-]")
        return this.replace(regex, "")
            .replace("\\s+".toRegex(), " ")
            .replace("--+".toRegex(), "-")

    }

    fun Date.formatDate(pattern: String): String =
        SimpleDateFormat(pattern, Locale.getDefault()).format(this)

    fun getCurrentDate(pattern: String): String =
        SimpleDateFormat(pattern, Locale.getDefault()).format(Date())

    fun String.formatStringDateToTime(): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date: Date = parser.parse(this)
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(date)
    }

    fun calculateTravelTime(departureTime: String, arrivalTime: String): String {
        val departureMinutes = timeToMinutes(departureTime)
        val arrivalMinutes = timeToMinutes(arrivalTime)

        val travelTimeMinutes = calculateTravelTime(departureMinutes, arrivalMinutes)

        val hours = travelTimeMinutes / 60
        val minutes = travelTimeMinutes % 60

        val formattedTravelTime = if (minutes >= 45) {
            hours + 1
        } else if (minutes >= 30 ){
            hours + 0.5
        } else  hours

        return formattedTravelTime.toString()
    }

    private fun timeToMinutes(time: String): Int {
        val (hours, minutes) = time.split(":").map { it.toInt() }
        return hours * 60 + minutes
    }

   private fun calculateTravelTime(departure: Int, arrival: Int): Int {
        var travelTime = arrival - departure
        if (travelTime < 0) {
            travelTime += 24 * 60
        }
        return travelTime
    }
}