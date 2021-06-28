package com.example.food2forkkmm.interactors.recipe_list

import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService
){
    fun execute(
        page:Int,
        query:String,
    ): Flow<DataState<List<Recipe>>> = flow {
        // Loading
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            // Returned data
            emit(DataState.data(data = recipes))
        }catch (e:Exception){
            // Error
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }

    }
}