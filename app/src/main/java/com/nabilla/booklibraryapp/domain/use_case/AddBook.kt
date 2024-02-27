package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

/**
 * A usecase class to add a book item in the list from repository
 */
class AddBook(private val repository: BookRepository) {

    operator fun invoke(book: Book){
        repository.insertBook(book)
    }

}