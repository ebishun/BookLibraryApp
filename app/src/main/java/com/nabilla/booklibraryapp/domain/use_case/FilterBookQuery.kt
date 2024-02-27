package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

class FilterBookQuery(private val repository: BookRepository) {

    operator fun invoke(searchString:String):List<Book> {
        return if (searchString.isEmpty()){
            repository.getBooks()
        }else{
            repository.filterBookByTitle(searchString)
        }

    }

}