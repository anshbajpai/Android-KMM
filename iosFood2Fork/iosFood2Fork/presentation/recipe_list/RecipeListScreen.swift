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
        
        if(viewModel.state.isLoading && viewModel.state.recipes.isEmpty){
            // Loading
            List{
                Rectangle()
                    .fill(Color.gray)
                    .opacity(0.5)
                    .frame(width: .infinity, height: 10).shimmering()
                    .cornerRadius(4.0)
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
            NavigationView{
                ZStack{
                    VStack{
                        SearchAppBar(
                            query: viewModel.state.query,
                            selectedCategory: viewModel.state.selectedCategory,
                            foodCategories: foodCategories,
                            onTriggerEvent: { event in
                                viewModel.onTriggerEvent(stateEvent: event)
                            }
                        )
                        List {
                            ForEach(viewModel.state.recipes, id: \.self.id){ recipe in
                                ZStack{
                                    RecipeCard(recipe: recipe)
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(recipe: recipe){
                                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                            }
                                        })
                                    NavigationLink(
                                        destination: RecipeDetailScreen(
                                            recipeId: Int(recipe.id),
                                            cacheModule: self.cacheModule
                                        )
                                    ){
                                        EmptyView()
                                    }
                                }
                                .listRowInsets(EdgeInsets())
                                .padding(.top, 10)
                                .padding(.leading, 6)
                                .padding(.trailing, 6)
                            }
                        }
                        .listStyle(PlainListStyle())
                    }
                }
                .navigationBarHidden(true)
            }
        }
    }
}
//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
