package ru.tk4dmitriy.feature.offers.api

class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int,
    val imgPreview: ByteArray,
)