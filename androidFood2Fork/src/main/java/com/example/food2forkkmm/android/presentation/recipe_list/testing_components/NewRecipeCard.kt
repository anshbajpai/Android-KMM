package com.example.food2forkkmm.android.presentation.recipe_list.testing_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.food2forkkmm.domain.model.Recipe


@Composable
fun NewRecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 8.dp,
                end = 8.dp
            )
            .fillMaxWidth()
            .border(
                width = 0.4.dp,
                color = MaterialTheme.colors.onSecondary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        
        Row() {

                NewRecipeImage(
                    url = recipe.featuredImage,
                    contentDescription = recipe.title,
                )

              Column(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
              ) {

                  Text(
                      text = recipe.title,
                      style = MaterialTheme.typography.h3,
                      color = Color.Black
                  )
                  
                  Spacer(modifier = Modifier.height(20.dp))
                  Row {
                      Icon(
                          Icons.Filled.Star,
                          contentDescription = "Localized description",
                          tint = Color(0xFFFFD700)

                      )
                      Text(
                          text = recipe.rating.toString(),
                          style = MaterialTheme.typography.h5
                      )
                  }

              }

        }

    }

}