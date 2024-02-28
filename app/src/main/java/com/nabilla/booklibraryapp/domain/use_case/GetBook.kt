package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

/**
 * A usecase class to retrieve a book object from repository
 */
class GetBook(private val repository: BookRepository) {
    operator fun invoke(id:Int):Book? {
        return repository.getBook(id)
    }

}