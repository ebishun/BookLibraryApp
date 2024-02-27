package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.model.InvalidEntryException
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class AddBook(private val repository: BookRepository) {

    @Throws(InvalidEntryException::class)
    operator fun invoke(book: Book){
        if (book.bookTitle.isEmpty()){
            throw InvalidEntryException("Book title cannot be empty")
        }

        if (book.bookAuthor.isEmpty()){
            throw InvalidEntryException("Book's author cannot be empty")
        }

        repository.insertBook(book)
    }

}