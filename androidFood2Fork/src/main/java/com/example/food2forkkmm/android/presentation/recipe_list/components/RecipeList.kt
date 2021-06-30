package com.example.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.example.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit
) {
    if(loading && recipes.isEmpty()){
       LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    }
    else if(recipes.isEmpty()){
        // Nothing
    }
    else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ){ index, recipe ->

                RecipeCard(recipe = recipe,
                    onClick = {
                        onClickRecipeListItem(recipe.id)
                    }
                )

            }
        }
    }
}