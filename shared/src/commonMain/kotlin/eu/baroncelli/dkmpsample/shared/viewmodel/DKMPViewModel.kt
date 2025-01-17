package eu.baroncelli.dkmpsample.shared.viewmodel

import eu.baroncelli.dkmpsample.shared.DebugLogger
import eu.baroncelli.dkmpsample.shared.datalayer.Repository
import kotlinx.coroutines.flow.StateFlow
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val debugLogger by lazy { DebugLogger("D-KMP SAMPLE") }


class DKMPViewModel (repo: Repository) {

    companion object Factory {
        // factory methods are defined in the platform-specific shared code (androidMain and iosMain)
    }

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    internal val stateManager by lazy { StateManager() }
    private val stateReducers by lazy { StateReducers(stateManager, repo) }
    val events by lazy { Events(stateReducers) }
    internal val stateProviders by lazy { StateProviders(stateManager, events) }

    fun getStartScreen() : Screen {
        return startScreen
    }

    fun onReEnterForeground() {
        // not called at app startup, but only when reentering the app after it was in background
        debugLogger.log("onReEnterForeground: recomposition is triggered")
        stateManager.triggerRecomposition()
    }

    fun onEnterBackground() {
        debugLogger.log("onEnterBackground: screen scopes are cancelled")
        stateManager.cancelScreenScopes()
    }


    fun exitScreen(oldRouteId: String, newRouteId: String) {
        debugLogger.log("exitScreen /$oldRouteId: state is removed, new routeId /$newRouteId")
        stateManager.removeScreen(oldRouteId, newRouteId)
    }

}