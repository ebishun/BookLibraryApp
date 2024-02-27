package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class DeleteBook (private val repository: BookRepository){

    operator fun invoke(book: Book){
        repository.deleteBook(book)
    }

}