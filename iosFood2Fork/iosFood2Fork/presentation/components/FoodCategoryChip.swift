//
//  FoodCategoryChip.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 02/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FoodCategoryChip: View {
    
    private let category: String
    private let isSelected: Bool
    
    init(
        category:String,
        isSelected: Bool = false
    ) {
        self.category = category
        self.isSelected = isSelected
    }
    
    var body: some View {
        HStack{
            Text(category) // TODO - FONT
                .padding(8)
                .background(isSelected ? Color.gray : Color.blue)
                .foregroundColor(Color.white)
        }
        .cornerRadius(6)
    }
}

struct FoodCategoryChip_Previews: PreviewProvider {
    static var previews: some View {
        FoodCategoryChip(
           category: "Chicken",
            isSelected: true
        )
    }
}
