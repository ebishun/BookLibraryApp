package com.nabilla.booklibraryapp.domain.model

/**
 * Book item class
 */
data class Book (
    var id:Int,
    val bookTitle:String,
    var bookAuthor:String,
    val bookSummary:String,
    val bookGenre:String,
    val bookLanguage:String,
    val bookResId:Int
)