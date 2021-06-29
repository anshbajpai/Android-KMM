package com.example.food2forkkmm.interactors.recipe_list

import com.example.food2forkkmm.datasource.cache.RecipeCache
import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
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

            kotlinx.coroutines.delay(500)
            //insert into cache
            recipeCache.insert(recipes)

            //query the cache
            val cacheResult = if (query.isBlank()){
                recipeCache.getAll(page = page)
            }
            else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }

            // emit List<Recipe> from cache
            emit(DataState.data(data = cacheResult))
        }catch (e:Exception){
            // Error
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }

    }
}