package com.example.food2forkkmm.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun RecipeImageList(
    url: String,
    contentDescription: String,
){
    val painter = rememberCoilPainter(request = url)
    Box{
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp,top = 0.dp, end = 4.dp,bottom = 0.dp)
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
                    .padding(start = 5.dp,top = 0.dp, end = 5.dp,bottom = 0.dp)
                    .height(RECIPE_IMAGE_HEIGHT.dp),
            ){

            }
        }
    }

}