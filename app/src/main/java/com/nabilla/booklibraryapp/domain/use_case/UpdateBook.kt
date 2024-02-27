package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

/**
 * A usecase class to update a book item from repository
 */
class UpdateBook(private val repository: BookRepository) {

    operator fun invoke(id:Int, book: Book) {
        repository.updateBook(id, book)
    }
}