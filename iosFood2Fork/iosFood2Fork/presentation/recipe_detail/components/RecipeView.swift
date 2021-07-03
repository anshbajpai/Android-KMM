//
//  RecipeView.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 03/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI
import shared

struct RecipeView: View {
    
    private let recipe: Recipe?
    private let dateUtil: DatetimeUtil
    private let message: GenericMessageInfo?
    private let onTriggerEvent: (RecipeDetailEvents) -> Void
    
    @State var showDialog: Bool
    
    init(
        recipe: Recipe?,
        dateUtil: DatetimeUtil,
        message: GenericMessageInfo? = nil,
        onTriggerEvent: @escaping (RecipeDetailEvents) -> Void
    ) {
        self.recipe = recipe
        self.dateUtil = dateUtil
        self.message = message
        if message != nil{
            self.showDialog = true
        }
        else {
            self.showDialog = false
        }
        self.onTriggerEvent = onTriggerEvent
    }
    
    var body: some View {
        NavigationView{
            ScrollView{
                if recipe == nil {
                    Text("Error")
                }
                else {
                    VStack(alignment: .leading){
                        WebImage(url: URL(string: recipe!.featuredImage))
                            .resizable()
                            .placeholder(Image(systemName: "photo")) // Placeholder Image
                            .placeholder {
                                Rectangle().foregroundColor(.white)
                            }
                            .indicator(.activity)
                            .transition(.fade(duration: 0.5))
                            .scaledToFill() // 1
                            .frame(height: 250, alignment: .center) // 2
                            .clipped() // 3
                        
                        VStack(alignment: .leading){
                            HStack(alignment: .lastTextBaseline) {
                                Text(
                                    "Updated \(dateUtil.humanizeDatetime(date: recipe!.dateUpdated)) by \(recipe!.publisher)"
                                )
                                .foregroundColor(Color.gray)
                                
                                Spacer()
                                
                                Text(String(recipe!.rating))
                                    .frame(alignment: .trailing)
                            }
                            
                            ForEach(recipe!.ingredients as Array<String> , id: \.self){ ingredient in
                                Text(ingredient)
                                    .font(Font.custom("Avenir", size: 16))
                                    .padding(.top, 4)
                            }
                        }
                        .background(Color.white)
                        .padding(12)
                    }
                }
            }
            .navigationBarHidden(true)
            .alert(
                isPresented: $showDialog,
                content: {
                    GenericMessageInfoAlert().build(
                        message: message!,
                        onRemoveHeadMessage: {
                            onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue())
                        })
                })
        }
        .navigationBarTitle(Text(recipe?.title ?? "Error"), displayMode: .inline)

    }
}

//struct RecipeView_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeView()
//    }
//}
