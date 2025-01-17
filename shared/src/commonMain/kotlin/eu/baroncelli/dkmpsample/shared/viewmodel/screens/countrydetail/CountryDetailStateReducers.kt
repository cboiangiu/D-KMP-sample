package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrydetail

import eu.baroncelli.dkmpsample.shared.datalayer.functions.getCountryInfo
import eu.baroncelli.dkmpsample.shared.viewmodel.StateManager
import eu.baroncelli.dkmpsample.shared.viewmodel.StateReducers

// here we define all state reducers for this screen,
// which are always called by the events

suspend fun StateReducers.updateCountryDetail(country: String) {
    val listItemData = dataRepository.getCountryInfo(country)
    // update CountryDetailState, after retrieving data from the Repository
    stateManager.updateScreen(CountryDetailState::class) {
        it.copy(countryInfo = listItemData, isLoading = false)
    }
}