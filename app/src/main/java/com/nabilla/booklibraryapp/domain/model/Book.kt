package com.nabilla.booklibraryapp.domain.model

data class Book (
    val id:Int,
    val bookTitle:String,
    val bookAuthor:String,
    val bookSummary:String,
    val bookGenre:String,
    val bookLanguage:String
)

class InvalidEntryException(message: String): Exception(message)