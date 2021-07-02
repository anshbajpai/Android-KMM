//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 01/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import Shimmer

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule:NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
    private let foodCategories: [FoodCategory]
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategoryUtil: foodCategoryUtil
        )
        self.foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    var body: some View {
        
        SearchAppBar(
            query: viewModel.state.query,
            selectedCategory: viewModel.state.selectedCategory,
            foodCategories: foodCategories,
            onTriggerEvent: {event in
                viewModel.onTriggerEvent(stateEvent: event)
            }
        )
        if(viewModel.state.isLoading && viewModel.state.recipes.isEmpty){
            // Loading
            List{
                ShimmerElement()
                ShimmerElement()
                ShimmerElement()
                ShimmerElement()
                ShimmerElement()
            }
        }
        else if(viewModel.state.recipes.isEmpty){
            // Nothing
        }
        else{
            List{
                ForEach(
                    viewModel.state.recipes, id: \.self.id
                ){ recipe in
                    RecipeCard(recipe: recipe)
                        .onAppear(perform: {
                            if(viewModel.shouldQueryNextPage(recipe: recipe)){
                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                           }
                        }
                    )
                        .listRowInsets(EdgeInsets())
                        .padding(.init(top: 10, leading: 6, bottom: 0, trailing: 6))
                        .background(Color.white)
                }
            }
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
