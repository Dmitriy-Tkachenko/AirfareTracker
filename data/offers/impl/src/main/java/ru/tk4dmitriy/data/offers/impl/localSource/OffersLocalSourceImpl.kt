package ru.tk4dmitriy.data.offers.impl.localSource

import android.content.Context
import kotlinx.serialization.json.Json
import ru.tk4dmitriy.core.utils.Utils.readAssetAsByteArray
import ru.tk4dmitriy.core.utils.Utils.readJsonFromAssets
import ru.tk4dmitriy.data.offers.api.Offer
import ru.tk4dmitriy.data.offers.impl.localSource.models.Response
import javax.inject.Inject

private const val FILE_NAME = "offers.json"

class OffersLocalSourceImpl @Inject constructor(
    private val context: Context
) : OffersLocalSource {
    override suspend fun getOffers(): List<Offer> {
        val json = context.assets.readJsonFromAssets(FILE_NAME)
        val offers = Json.decodeFromString<Response>(json)
        return offers.mapResponseToOffersData()
    }

    private fun getPreviewByteArray(offerId: Int): ByteArray {
        val previewFileName = when (offerId) {
            1 -> Preview.PREVIEW_1.fileName
            2 -> Preview.PREVIEW_2.fileName
            else -> Preview.PREVIEW_3.fileName
        }
        return context.assets.readAssetAsByteArray(previewFileName)
    }

    private enum class Preview(val fileName: String) {
        PREVIEW_1("preview_1.png"),
        PREVIEW_2("preview_2.png"),
        PREVIEW_3("preview_3.png")
    }

    private fun Response.mapResponseToOffersData(): List<Offer> = this.offers.map {
        Offer(
            id = it.id,
            title = it.title,
            town = it.town,
            price = it.price.value,
            imgPreview = getPreviewByteArray(it.id)
        )
    }
}