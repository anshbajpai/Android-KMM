package com.example.food2forkkmm.datasource.cache

import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DatetimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase():RecipeDatabase{
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

// Extension Functions

fun Recipe_Entity.toRecipe():Recipe{
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added),
        dateUpdated = datetimeUtil.toLocalDate(date_updated),
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe>{
    return map{ it.toRecipe() }
}

fun List<String>.convertIngredientListToString():String{
    val ingredientString = StringBuilder()
    for(ingredient in this){
        ingredientString.append("$ingredient,")
    }

    return ingredientString.toString()
}

fun String.convertIngredientsToList(): List<String>{
    val list: ArrayList<String> = ArrayList()
    for (ingredient in split(",")){
        list.add(ingredient)
    }

    return list
}