package com.nabilla.booklibraryapp.data.repository

import android.util.Log
import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class FakeRepository : BookRepository {

    val myFakeBooks = mutableListOf<Book>()
    var myFakeId = 0

    override fun getBooks(): List<Book> {
        return myFakeBooks.toList()
    }

    override fun getBook(id: Int): Book? {
        myFakeBooks.find { it.id == id }
        return myFakeBooks.find { it.id == id }
    }

    override fun insertBook(book: Book) {
        myFakeId += 1
        book.id = myFakeId
        myFakeBooks.add(book)
    }

    override fun deleteBook(book: Book) {
        myFakeBooks.remove(book)
    }

    override fun updateBook(id:Int, book: Book) {
        //books[id] = book
        val updateIdx = myFakeBooks.indexOfFirst { it.id == id }    //Get book index in the list containing said id
        myFakeBooks[updateIdx] = book
    }

    override fun filterBookByTitle(searchString: String): List<Book> {
        return myFakeBooks.filter { it.bookTitle.contains(searchString, ignoreCase = true) }
    }
}