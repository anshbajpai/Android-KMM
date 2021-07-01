package com.example.food2forkkmm.presentation.recipe_detail

import com.example.food2forkkmm.domain.model.GenericMessageInfo
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading:Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
)
