package com.example.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.food2forkkmm.android.presentation.components.RecipeImage
import com.example.food2forkkmm.android.presentation.navigation.Screen
import com.example.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.example.food2forkkmm.android.presentation.theme.AppTheme
import com.example.food2forkkmm.domain.model.Recipe

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    AppTheme(displayProgressBar = false) {
        if(recipe == null){
            Text(text = "Unable to get details!!")
        }
        else{
            RecipeCard(recipe = recipe,
                onClick = {}
            )
        }
    }
}