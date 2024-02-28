package com.nabilla.booklibraryapp.domain.model

data class Book (
    var id:Int,
    val bookTitle:String,
    val bookAuthor:String,
    val bookSummary:String,
    val bookGenre:String,
    val bookLanguage:String,
    val bookResId:Int
)