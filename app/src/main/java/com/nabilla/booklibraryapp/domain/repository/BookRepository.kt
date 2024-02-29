package com.nabilla.booklibraryapp.domain.repository

import com.nabilla.booklibraryapp.domain.model.Book

/**
 * Repository class interface
 * UseCases class will interact with repository through here
 */
interface BookRepository {
    fun getBooks(): List<Book>
    fun getBook(id:Int): Book?
    fun insertBook(book: Book)
    fun deleteBook(book: Book)
    fun updateBook(id:Int, book: Book)
    fun filterBookByTitle(searchString:String): List<Book>
}