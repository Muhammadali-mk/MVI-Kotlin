package uz.uzkassa.app.controller

import android.util.Log
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import uz.uzkassa.app.extensions.mapNotNull
import uz.uzkassa.app.mapper.eventToIntent
import uz.uzkassa.app.mapper.stateToModel
import uz.uzkassa.app.store.CalculatorStore
import uz.uzkassa.app.store.CalculatorStoreFactory
import uz.uzkassa.app.view.CalculatorView

class CalculatorController {
    private val store: CalculatorStore = CalculatorStoreFactory(DefaultStoreFactory).create()
    private var binder: Binder? = null

    init {
        Log.wtf("Calculator controller created", "......")
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun onViewCreated(
        view: CalculatorView,
        viewLifecycle: com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
    ) {
        binder = bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(stateToModel) bindTo view
            view.events.mapNotNull(eventToIntent) bindTo store
        }
    }

    fun onStart() {
        binder?.start()
    }

    fun onStop() {
        binder?.stop()
    }

    fun onViewDestroyed() {
        binder = null
    }

    fun onDestroy() {
        store.dispose()
    }
}