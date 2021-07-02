//
//  ShimmerElement.swift
//  iosFood2Fork
//
//  Created by Ansh Bajpai on 02/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shimmer

struct ShimmerElement: View {
    var body: some View {
        HStack{
            Rectangle()
                .fill(Color.gray)
                .opacity(0.5)
                .frame(width: 200, height: 210).redacted(reason: .placeholder).shimmering()
                .cornerRadius(4.0)
            
            VStack (alignment: .leading){
                Rectangle()
                    .fill(Color.gray)
                    .opacity(0.5)
                    .frame(height: 15).redacted(reason: .placeholder).shimmering()
                    .cornerRadius(4.0)
                
                Spacer().frame(height: 5)
                
                Rectangle()
                    .fill(Color.gray)
                    .opacity(0.5)
                    .frame(height: 15).redacted(reason: .placeholder).shimmering()
                    .cornerRadius(4.0)
                
                Spacer()
            }
        }
    }
}

