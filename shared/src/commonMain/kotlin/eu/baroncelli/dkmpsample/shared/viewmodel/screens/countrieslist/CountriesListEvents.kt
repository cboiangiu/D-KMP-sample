package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist

import eu.baroncelli.dkmpsample.shared.viewmodel.Events

/********** INTERNAL event function, called by the StateProvider's callOnInit **********/

internal fun Events.loadCountriesListData() = screenCoroutine {
    stateReducers.updateCountriesList(null)
}


/********** PUBLIC event functions, called directly by the UI layer **********/

fun Events.selectMenuItem(menuItem: MenuItem) = screenCoroutine {
    stateReducers.updateCountriesList(menuItem)
}

fun Events.selectFavorite(country: String) = screenCoroutine {
    stateReducers.toggleFavorite(country)
}