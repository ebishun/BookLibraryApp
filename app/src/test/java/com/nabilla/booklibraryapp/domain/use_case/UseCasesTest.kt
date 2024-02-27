package com.nabilla.booklibraryapp.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.nabilla.booklibraryapp.data.repository.FakeRepository
import com.nabilla.booklibraryapp.domain.model.Book
import org.junit.Before
import org.junit.Test

class UseCasesTest {

    private lateinit var getBooks: GetBooks
    private lateinit var addBook: AddBook
    private lateinit var deleteBook: DeleteBook
    private lateinit var updateBook: UpdateBook
    private lateinit var fakeRepository: FakeRepository
    //var testBookList = mutableListOf<Book>()

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getBooks = GetBooks(fakeRepository)
        addBook = AddBook(fakeRepository)
        deleteBook = DeleteBook(fakeRepository)
        updateBook = UpdateBook(fakeRepository)
        //testBookList = fakeRepository.myFakeBooks
    }


    @Test
    fun testAddBook(){
        val addBookItem = Book(
            1,"Book 1","Author 1","Summary 1","Genre 1","English",0
        )
        addBook.invoke(addBookItem)
        assertThat(fakeRepository.myFakeBooks).contains(addBookItem)
    }
}