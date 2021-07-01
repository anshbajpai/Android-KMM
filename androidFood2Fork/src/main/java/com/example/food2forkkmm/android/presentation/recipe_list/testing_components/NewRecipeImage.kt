package com.example.food2forkkmm.android.presentation.recipe_list.testing_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HEIGHT = 210
@Composable
fun NewRecipeImage(
    url: String,
    contentDescription: String,
){
    val painter = rememberCoilPainter(request = url, fadeIn = true)
    Box{
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(RECIPE_IMAGE_HEIGHT.dp),

            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop

        )
    }

    when(painter.loadState){
        is ImageLoadState.Error -> {
            //Show Error
        }
        is ImageLoadState.Success -> {
            // Success Message
        }
        is ImageLoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(RECIPE_IMAGE_HEIGHT.dp),
            ){

            }
        }
    }

}
