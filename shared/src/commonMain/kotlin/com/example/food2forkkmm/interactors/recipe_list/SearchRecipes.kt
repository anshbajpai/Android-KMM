package com.example.food2forkkmm.interactors.recipe_list

import com.example.food2forkkmm.datasource.cache.RecipeCache
import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.domain.model.GenericMessageInfo
import com.example.food2forkkmm.domain.model.PositiveAction
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.model.UIComponentType
import com.example.food2forkkmm.domain.util.CommonFlow
import com.example.food2forkkmm.domain.util.DataState
import com.example.food2forkkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
){
    fun execute(
        page:Int,
        query:String,
    ): CommonFlow<DataState<List<Recipe>>> = flow {
        // Loading
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )

            kotlinx.coroutines.delay(500)

            if(query == "error"){
                throw Exception("Forcing an error... Search FAILED")
            }

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
            emit(DataState.error<List<Recipe>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?:"Unknown Error")
                    .positive(
                        PositiveAction(
                            positiveBtnTxt = "OK",
                            onPositiveAction = {}
                        )
                    )

            ))
        }

    }.asCommonFlow()
}