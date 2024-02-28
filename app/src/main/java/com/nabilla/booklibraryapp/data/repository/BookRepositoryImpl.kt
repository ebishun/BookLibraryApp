package com.nabilla.booklibraryapp.data.repository

import android.util.Log
import com.nabilla.booklibraryapp.R
import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class BookRepositoryImpl():BookRepository {

    private val books = mutableListOf<Book>()
    private var myId = 0
    init {
        books.add(Book(1,"The Jakarta Method","Vincent Bevins","A 2020 political history book by American journalist and author Vincent Bevins. It concerns U.S. government support for and complicity in anti-communist mass killings around the world and their aggregate consequences from the Cold War until the present era.",
            "Non-Fiction","English", R.drawable.book1))
        books.add(Book(2,"A Thousand Splendid Suns","Khaled Hosseini","A beautiful narrative about two women in Afghanistan during the second half of the 20th century",
            "Fiction","English",R.drawable.book2))
        books.add(Book(3,"The Sympathizer","Viet Thanh Nguyen","A half-French, half-Vietnamese man serves as a spy for the Communist forces during the Vietnam War.",
            "Historical Fiction","English",R.drawable.book3))
        books.add(Book(4,"The Lizard Cage","Karen Connelly","The story is set in a Burmese prison and revolves around the life of Teza, a political prisoner and a musician, as well as the interconnected lives of the various characters within the prison and its surroundings.",
            "Historical Fiction","English",R.drawable.book4))
        books.add(Book(5,"Crayon Shin Chan","Yoshito Usui","Crayon Shin-chan follows the daily shenanigans of Shin-chan with his group of friends, parading around as the self-proclaimed Kasukabe Defense Force.",
            "Manga","English",R.drawable.book5))
        myId = books.size
    }

    override fun getBooks(): List<Book> {
        return books.toList()
    }

    override fun getBook(id: Int): Book? {
        books.find { it.id == id }
        //return books[id]
        return books.find { it.id == id }
    }

    override fun insertBook(book: Book) {
        myId += 1
        book.id = myId
        Log.e("BookRepository","id_added: ${book.id}")
        books.add(book)
    }

    override fun deleteBook(book: Book) {
        books.remove(book)
    }

    override fun updateBook(id:Int, book: Book) {
        //books[id] = book
        val updateIdx = books.indexOfFirst { it.id == id }
        books[updateIdx] = book
    }

    override fun filterBookByTitle(searchString: String): List<Book> {
        return books.filter { it.bookTitle.contains(searchString, ignoreCase = true) }
    }


}