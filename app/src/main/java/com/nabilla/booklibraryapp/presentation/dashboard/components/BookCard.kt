package com.nabilla.booklibraryapp.presentation.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nabilla.booklibraryapp.domain.model.Book


/**
 * A Composable function representing a card containing book's item.
 * title, author, summary
 */
@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier,
    imageResId: Int
) {
    Card(
        modifier = modifier
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            
            //Image container
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null, // contentDescription is set to null as it's decorative
                    modifier = Modifier
                        .height(140.dp)
                        .padding(10.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            //Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {

                Text(
                    text = book.bookTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier,
                    text = "by ${book.bookAuthor}",
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = book.bookSummary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.height(70.dp)
                )

            }
        }


    }

}