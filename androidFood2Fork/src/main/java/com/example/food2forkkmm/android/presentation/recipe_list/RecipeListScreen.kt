package com.example.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food2forkkmm.android.presentation.components.GradientDemo
import com.example.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.example.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.example.food2forkkmm.android.presentation.theme.AppTheme
import com.example.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.example.food2forkkmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
){
    AppTheme(displayProgressBar = state.isLoading) {

      RecipeList(
          loading = state.isLoading,
          recipes = state.recipes,
          page = state.page,
          { onTriggerEvent(RecipeListEvents.NextPage)},
          onClickRecipeListItem = onClickRecipeListItem,
      )


    }

}