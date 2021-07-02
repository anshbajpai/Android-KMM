//
//  RecipeCard.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 02/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI
import Shimmer

struct RecipeCard: View {
    
    private let recipe:Recipe
    
    init(
        recipe: Recipe
    ) {
        self.recipe = recipe
    }
    
    var body: some View {
        HStack{
            WebImage(url: URL(string: recipe.featuredImage))
                .resizable()
                .placeholder(Image(systemName: "photo"))
                .placeholder{
                    Rectangle().foregroundColor(.white)
                }
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .scaledToFill()
                .frame(width: 200, height: 210, alignment: .center)
                .cornerRadius(4.0, corners: [.topLeft, .bottomLeft])
            
            VStack(
                alignment: .leading
            ) {
                Text(recipe.title)
                    .font(.body)
                
                HStack{
                    Image(systemName: "star.fill")
                        .imageScale(/*@START_MENU_TOKEN@*/.medium/*@END_MENU_TOKEN@*/)
                        .foregroundColor(.yellow)
                    
                    Text(String(recipe.rating))
                }
                .padding(.top, 5)
                
            }
             .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: 210, alignment: .topLeading)
             .padding(.init(top: 8, leading: 6, bottom: 12, trailing: 6))
        }
        .background(Color.white)
        .cornerRadius(4.0)
        .shadow(radius: 5)
    }
}

extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape( RoundedCorner(radius: radius, corners: corners) )
    }
}

struct RoundedCorner: Shape {

    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}

//struct RecipeCard_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeCard()
//    }
//}
