package com.nabilla.booklibraryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nabilla.booklibraryapp.domain.util.Routes
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nabilla.booklibraryapp.presentation.add_edit_book.AddEditBookScreen
import com.nabilla.booklibraryapp.presentation.dashboard.DashboardScreen

import com.nabilla.booklibraryapp.ui.theme.BookLibraryAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Starting Activity.
 * Declaration for NavHost is made here
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookLibraryAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.DASHBOARD
                    ) {
                        composable(
                            route = Routes.DASHBOARD
                        ) {
                            DashboardScreen(navController = navController)
                        }

                        composable(
                            route = Routes.ADD_EDIT_BOOK + "?bookId={bookId}",
                            arguments = listOf(
                                navArgument(name = "bookId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        )
                        {
                            val bookId = it.arguments?.getInt("bookId") ?: -1
                            AddEditBookScreen(navController = navController, bookId = bookId)
                        }
                    }
                }
            }
        }
    }
}
