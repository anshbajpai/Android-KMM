package com.example.food2forkkmm.presentation.recipe_list

import com.example.food2forkkmm.domain.model.GenericMessageInfo
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.Queue

actual data class RecipeListState(
    val isLoading:Boolean =false,
    val page:Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes:List<Recipe> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)