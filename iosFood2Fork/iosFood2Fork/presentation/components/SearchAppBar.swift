//
//  SearchAppBar.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 02/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query: String
    private let selectedCategory: FoodCategory?
    private let foodCategories: [FoodCategory]
    private let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(
        query: String,
        selectedCategory: FoodCategory?,
        foodCategories:[FoodCategory],
        onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ) {
        self.onTriggerEvent = onTriggerEvent
        self.foodCategories = foodCategories
        self.selectedCategory = selectedCategory
        self._query = State(initialValue: query)
    }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit: {
                        onTriggerEvent(RecipeListEvents.NewSearch())
                    }
                )
                .onChange(of: query, perform: { value in
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                })
            }
            .padding(.bottom, 8)
            ScrollView(.horizontal){
                HStack(spacing: 10){
                    ForEach(foodCategories, id: \.self){ category in
                        FoodCategoryChip(category: category.value,
                                         isSelected: selectedCategory == category)
                            .onTapGesture {
                                query = category.value
                                onTriggerEvent(RecipeListEvents.OnSelectCategory(category: category))
                            }
                    }
                }
            }
        }
        .padding(.top, 8)
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}

//struct SearchAppBar_Previews: PreviewProvider {
//    static var previews: some View {
//        SearchAppBar()
//    }
//}
