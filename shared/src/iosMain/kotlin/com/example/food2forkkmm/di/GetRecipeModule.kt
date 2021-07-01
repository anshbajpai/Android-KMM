package com.example.food2forkkmm.di

import com.example.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.example.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents

class GetRecipeModule(
    private val cacheModule: CacheModule,
) {
    val getRecipe: GetRecipe by lazy {
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }
}