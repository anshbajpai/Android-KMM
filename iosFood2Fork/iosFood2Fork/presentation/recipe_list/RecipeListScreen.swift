//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 01/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule:NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
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
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategoryUtil: FoodCategoryUtil()
        )
    }
    
    var body: some View {
        VStack {
            Text("\(viewModel.state.page)")
            Button(
                action: {
                    viewModel.updateState(page: Int(viewModel.state.page) + 1)
                },
                label: {
                    Text("Increment Page")
                }
            )
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
