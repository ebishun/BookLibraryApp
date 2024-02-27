package com.nabilla.booklibraryapp.domain.repository

import com.nabilla.booklibraryapp.domain.model.Book

interface BookRepository {

    fun getBooks(): List<Book>
    fun insertBook(book: Book)
    fun deleteBook(book: Book)
    fun updateBook(id:Int, book: Book)
}