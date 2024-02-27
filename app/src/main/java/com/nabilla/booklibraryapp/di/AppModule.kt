package com.nabilla.booklibraryapp.di

import com.nabilla.booklibraryapp.data.repository.BookRepositoryImpl
import com.nabilla.booklibraryapp.domain.repository.BookRepository
import com.nabilla.booklibraryapp.domain.use_case.AddBook
import com.nabilla.booklibraryapp.domain.use_case.BookUseCases
import com.nabilla.booklibraryapp.domain.use_case.DeleteBook
import com.nabilla.booklibraryapp.domain.use_case.GetBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookRepository():BookRepository{
        return BookRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideBookUseCases(repository: BookRepository):BookUseCases{
        return BookUseCases(
            getBooks = GetBooks(repository),
            addBook = AddBook(repository),
            deleteBook = DeleteBook(repository)
        )
    }

}