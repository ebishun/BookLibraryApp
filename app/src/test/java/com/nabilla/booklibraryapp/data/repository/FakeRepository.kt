package com.nabilla.booklibraryapp.data.repository

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class FakeRepository : BookRepository {

    val myFakeBooks = mutableListOf<Book>()

    override fun getBooks(): List<Book> {
        return myFakeBooks.toList()
    }

    override fun getBook(id: Int): Book {
        return myFakeBooks[id]
    }

    override fun insertBook(book: Book) {
        myFakeBooks.add(book)
    }

    override fun deleteBook(book: Book) {
        myFakeBooks.remove(book)
    }

    override fun updateBook(id:Int, book: Book) {
        myFakeBooks[id] = book
    }

    override fun filterBookByTitle(searchString: String): List<Book> {
        return myFakeBooks.toList()
    }
}