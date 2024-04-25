package ru.tk4dmitriy.core.utils

import android.content.res.AssetManager
import android.icu.text.SimpleDateFormat
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
}