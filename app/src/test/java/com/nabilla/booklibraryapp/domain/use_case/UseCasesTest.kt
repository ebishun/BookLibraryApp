package com.nabilla.booklibraryapp.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.nabilla.booklibraryapp.data.repository.FakeRepository
import com.nabilla.booklibraryapp.domain.model.Book
import org.junit.Before
import org.junit.Test
import com.nabilla.booklibraryapp.R

class UseCasesTest {

    private lateinit var getBooks: GetBooks
    private lateinit var addBook: AddBook
    private lateinit var deleteBook: DeleteBook
    private lateinit var updateBook: UpdateBook
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getBooks = GetBooks(fakeRepository)
        addBook = AddBook(fakeRepository)
        deleteBook = DeleteBook(fakeRepository)
        updateBook = UpdateBook(fakeRepository)
    }

    @Test
    fun testAddBook(){
        val addBookItem = Book(
            1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6
        )
        addBook.invoke(addBookItem)
        assertThat(fakeRepository.myFakeBooks).contains(addBookItem)
    }

    @Test
    fun testGetBooks(){
        val book1 = Book(1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Book 2","Author 2","Summary 2","Genre 2","English",R.drawable.book6)
        val book3 = Book(3,"Book 3","Author 3","Summary 3","Genre 1","English",R.drawable.book6)
        val book4 = Book(4,"Book 4","Author 4","Summary 4","Genre 2","English",R.drawable.book6)

        addBook.invoke(book1)
        addBook.invoke(book2)
        addBook.invoke(book3)
        addBook.invoke(book4)

        assertThat(fakeRepository.getBooks().size).isEqualTo(4)
        assertThat(fakeRepository.getBooks()).contains(book3)
    }

    @Test
    fun testUpdateBook(){
        val book1 = Book(1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Book 2","Author 2","Summary 2","Genre 2","English",R.drawable.book6)

        addBook.invoke(book1)
        addBook.invoke(book2)
        assertThat(fakeRepository.getBook(2)?.bookAuthor).containsMatch("Author 2")
        book2.bookAuthor = "Nabilla Omar"

        updateBook(2,book2)
        assertThat(fakeRepository.getBook(2)?.bookAuthor).containsMatch("Nabilla Omar")
    }

    @Test
    fun testDeleteBook(){
        val book1 = Book(1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Book 2","Author 2","Summary 2","Genre 2","English",R.drawable.book6)
        val book3 = Book(3,"Book 3","Author 3","Summary 3","Genre 1","English",R.drawable.book6)
        val book4 = Book(4,"Book 4","Author 4","Summary 4","Genre 2","English",R.drawable.book6)
        addBook.invoke(book1)
        addBook.invoke(book2)
        addBook.invoke(book3)
        addBook.invoke(book4)
        assertThat(fakeRepository.myFakeBooks).contains(book4)
        deleteBook.invoke(book4)
        assertThat(fakeRepository.myFakeBooks).doesNotContain(book4)
    }

    @Test
    fun testDeleteBookInTheMiddleIndex(){
        val book1 = Book(1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Book 2","Author 2","Summary 2","Genre 2","English",R.drawable.book6)
        val book3 = Book(3,"Book 3","Author 3","Summary 3","Genre 1","English",R.drawable.book6)
        val book4 = Book(4,"Book 4","Author 4","Summary 4","Genre 2","English",R.drawable.book6)

        addBook.invoke(book1)
        addBook.invoke(book2)
        addBook.invoke(book3)
        addBook.invoke(book4)
        assertThat(fakeRepository.myFakeBooks).contains(book2)

        deleteBook.invoke(book2)
        assertThat(fakeRepository.myFakeBooks).doesNotContain(book2)
    }

    @Test
    fun testSelectBook(){
        val book1 = Book(1,"Book 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Book 2","Author 2","Summary 2","Genre 2","English",R.drawable.book6)
        val book3 = Book(3,"Book 3","Author 3","Summary 3","Genre 1","English",R.drawable.book6)
        val book4 = Book(4,"Book 4","Author 4","Summary 4","Genre 2","English",R.drawable.book6)
        val book5 = Book(5,"Book 5","Author 5","Summary 5","Genre 2","English",R.drawable.book6)

        addBook.invoke(book1)
        addBook.invoke(book2)
        addBook.invoke(book3)
        addBook.invoke(book4)
        addBook.invoke(book5)

        assertThat(fakeRepository.getBook(3)?.bookAuthor).doesNotContain("Author 4")
        assertThat(fakeRepository.getBook(3)?.bookAuthor).contains("Author 3")

        deleteBook.invoke(book2)            //Delete Book 2

        assertThat(fakeRepository.getBook(2)?.bookAuthor).isNull()
        assertThat(fakeRepository.getBook(4)?.bookAuthor).contains("Author 4")

    }

    @Test
    fun testFilterBookListByQuery(){
        val book1 = Book(1,"My Name 1","Author 1","Summary 1","Genre 1","English",R.drawable.book6)
        val book2 = Book(2,"Hello World","Author 2","Summary 2","Genre 2","English",R.drawable.book6)
        val book3 = Book(3,"Foodie Adventure","Author 3","Summary 3","Genre 1","English",R.drawable.book6)
        val book4 = Book(4,"My Planet Mars","Author 4","Summary 4","Genre 2","English",R.drawable.book6)
        val book5 = Book(5,"Lost Pluto","Author 5","Summary 5","Genre 2","English",R.drawable.book6)

        addBook.invoke(book1)
        addBook.invoke(book2)
        addBook.invoke(book3)
        addBook.invoke(book4)
        addBook.invoke(book5)

        val myList = fakeRepository.filterBookByTitle("Planet")
        assertThat(myList.size).isGreaterThan(0)
        assertThat(myList[0].bookAuthor).containsMatch("Author 4")

        deleteBook.invoke(book4)

        val myList2 = fakeRepository.filterBookByTitle("Planet")
        assertThat(myList2.size).isEqualTo(0)

    }
}