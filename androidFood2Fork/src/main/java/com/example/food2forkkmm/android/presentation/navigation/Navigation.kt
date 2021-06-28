package com.example.food2forkkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.example.food2forkkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.example.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.example.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel

@ExperimentalStdlibApi
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){
        composable(route = Screen.RecipeList.route){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(
                onSelectedRecipe = { recipeId ->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(route = Screen.RecipeDetail.route + "/{recipeId}"
            , arguments = listOf(navArgument("recipeId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
                RecipeDetailScreen(recipe = viewModel.recipe.value)

        }
    }
}