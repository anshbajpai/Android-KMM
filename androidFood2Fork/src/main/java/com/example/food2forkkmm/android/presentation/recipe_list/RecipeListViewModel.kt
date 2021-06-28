package com.example.food2forkkmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes,
):ViewModel()
{
    init {
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = 1,
            query = "chicken"
        ).onEach { dataState ->
            println("RecipeListV: ${dataState.isLoading}")

            dataState.data?.let {
                println("RecipeListV: recipes: ${it}")
            }

            dataState.message?.let {
                println("RecipeListV: error: ${it}")
            }
        }.launchIn(viewModelScope)
    }
}
