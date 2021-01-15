package uz.uzkassa.app.store

import com.arkivanov.mvikotlin.core.store.Store

interface CalculatorStore : Store<CalculatorStore.Intent, CalculatorStore.State, Nothing> {

    sealed class Intent {
        object Increment : Intent()
        object Decrement : Intent()
        data class Sum(val n :Int):Intent()
    }

    data class State(val value: Long = 0L)
}