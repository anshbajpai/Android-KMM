//
//  RecipeDetailScreen.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 03/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {
    
    private let cacheModule: CacheModule
    private let getRecipeModule: GetRecipeModule
    private let recipeId: Int
    private let datetimeUtil = DatetimeUtil()
    
    @ObservedObject var viewModel: RecipeDetailViewModel
    
    init(
        recipeId: Int,
        cacheModule: CacheModule
         ) {
        self.recipeId = recipeId
        self.cacheModule = cacheModule
        self.getRecipeModule = GetRecipeModule(
            cacheModule: self.cacheModule
        )
        viewModel = RecipeDetailViewModel(
            recipeId: self.recipeId,
            getRecipe: self.getRecipeModule.getRecipe
        )
    }
    
    var body: some View {
            RecipeView(
                recipe: viewModel.state.recipe,
                dateUtil: datetimeUtil,
                message: viewModel.state.queue.peek(),
                onTriggerEvent: viewModel.onTriggerEvent
                
            )
    }
}
