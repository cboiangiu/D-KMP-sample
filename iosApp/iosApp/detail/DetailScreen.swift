//
//  DetailScreen.swift
//
//  Created by Daniele Baroncelli on 13/03/2021.
//

import SwiftUI
import shared

struct DetailScreen: View {
    var countryName : String
    @EnvironmentObject var viewModel: AppViewModel
    
    var body: some View {
        let state = viewModel.stateProvider.getDetail(country: countryName)
        VStack {
            if state.isLoading {
                LoadingScreen()
            } else {
                DetailContent(data: state.countryInfo)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .principal) {
                Text(countryName).font(.headline).foregroundColor(.white)
            }
        }
    }
}