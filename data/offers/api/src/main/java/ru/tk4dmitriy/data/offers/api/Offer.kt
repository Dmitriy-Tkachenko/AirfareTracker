package ru.tk4dmitriy.data.offers.api

class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int,
    val imgPreview: ByteArray
)