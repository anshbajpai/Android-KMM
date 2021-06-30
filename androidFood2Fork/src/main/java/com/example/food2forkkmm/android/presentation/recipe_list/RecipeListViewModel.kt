package com.example.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.example.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.example.food2forkkmm.presentation.recipe_list.RecipeListState
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

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }


    fun onTriggerEvent(event: RecipeListEvents){
        when(event){
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }



    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let {
                appendRecipes(it)
            }

            dataState.message?.let {
                handleError("Error")
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)

    }

    private fun handleError(errorMessage: String){

    }
}
